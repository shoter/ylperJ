Use YlperJ;
DELIMITER //
CREATE PROCEDURE Find_Car_For_Booking(
IN p_start_time DATETIME,
IN p_end_time DATETIME,
IN p_luxury_category_id INT,
IN p_car_features VARCHAR(2000),
IN p_point POINT
)
BEGIN
drop temporary table if exists features;
create temporary table features( val integer );


IF p_car_features IS NOT NULL AND p_car_features != '' THEN
BEGIN

set @sql = concat("insert into features (val) values ", p_car_features);
prepare stmt1 from @sql;
execute stmt1;
END;
END IF;

select count(val) from features into @feature_count;

Select car.id carId, location.location location, ST_DISTANCE(location.location, p_point) distance from Cars car
JOIN CarModels model on car.CarModelId = model.Id
JOIN CarLuxuryCategories luxury on model.LuxuryCategoryId = luxury.Id
#Finds last time for a car
JOIN CarLocationHistories location ON location.CarId = car.Id
JOIN (select CarId, MAX(DateTime) DateTime FROM CarLocationHistories WHERE DateTime <= p_start_time GROUP BY CarId) sl ON location.CarId = sl.CarId AND location.DateTime = sl.DateTime
WHERE
# Finds desired features
(car.Id IN (SELECT CarId from CarAssignedFeatures
Where CarFeatureId IN (select val from features)
group by CarId Having count(carId) = @feature_count)
OR @feature_count = 0)
# Finds if there is a collision with booking
AND car.Id NOT IN (select CarId from Bookings
where (p_start_time BETWEEN StartDateTime AND EndDateTime) OR (p_end_time BETWEEN StartDateTime AND EndDateTime))
# Finds if we have good luxury category
AND (luxury.Id = p_luxury_category_id OR p_luxury_category_id IS NULL)
ORDER BY distance;


END //
DELIMITER ;