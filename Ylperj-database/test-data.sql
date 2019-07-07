INSERT INTO Cars(CarModelId, CreateDate)
VALUES(1, '2019-06-30'); -- cheap skoda diesel

SET @last_id= LAST_INSERT_ID();
SET @car_a = @last_id;

INSERT INTO CarAssignedFeatures(CarId, CarFeatureId) VALUES
(@last_id, 1); -- radio

-- I have those values hardcoded here to save some time. This is not best practice but here I did it to save time.
-- If I would do that for bigger/enterprise program then I would create external program to do that. Probably would do it with python.

INSERT INTO Cars(CarModelId, CreateDate)
VALUES(34, '2019-06-30'); -- lux volv el

SET @last_id= LAST_INSERT_ID();
SET @car_b = @last_id;

INSERT INTO CarAssignedFeatures(CarId, CarFeatureId) VALUES
(@last_id, 1), -- radio
(@last_id, 2), -- CB
(@last_id, 3), -- GPS
(@last_id, 4); -- Leather Seats

--

INSERT INTO Cars(CarModelId, CreateDate)
VALUES(20, '2019-06-30'); -- standard fiat gasoline

SET @last_id= LAST_INSERT_ID();
SET @car_c = @last_id;

INSERT INTO CarAssignedFeatures(CarId, CarFeatureId) VALUES
(@last_id, 1), -- radio
(@last_id, 3); -- GPS

--

INSERT INTO Cars(CarModelId, CreateDate)
VALUES(7, '2019-06-30'); -- std toyota diesel

SET @last_id= LAST_INSERT_ID();
SET @car_d = @last_id;

INSERT INTO CarAssignedFeatures(CarId, CarFeatureId) VALUES
(@last_id, 1), -- radio
(@last_id, 2); -- CB

INSERT INTO Users(Username, Name, GenderId, Birthday, CreateDate)
VALUES ('test', 'test account', 3, '1990-02-04', '2019-06-30');

SET @user_id = LAST_INSERT_ID();

INSERT INTO Bookings(UserId, CarId, StartDateTime, PickupPosition, EndDateTime, DropPosition)
VALUES (1, @car_a, '2019-07-01 13:00', POINT(1,3), '2019-07-01 15:00', POINT(2,1));
INSERT INTO Bookings(UserId, CarId, StartDateTime, PickupPosition, EndDateTime, DropPosition)
VALUES (1, @car_a, '2019-07-01 19:00', POINT(2,1), '2019-07-01 20:00', POINT(4,3));

INSERT INTO Bookings(UserId, CarId, StartDateTime, PickupPosition, EndDateTime, DropPosition)
VALUES (1, @car_b, '2019-07-01 14:00', POINT(1,1), '2019-07-01 19:00', POINT(3,3));

INSERT INTO Bookings(UserId, CarId, StartDateTime, PickupPosition, EndDateTime, DropPosition)
VALUES (1, @car_c, '2019-07-01 12:00', POINT(1,1), '2019-07-01 13:00', POINT(1,3));
INSERT INTO Bookings(UserId, CarId, StartDateTime, PickupPosition, EndDateTime, DropPosition)
VALUES (1, @car_c, '2019-07-01 14:00', POINT(1,3), '2019-07-01 16:00', POINT(3,3));
INSERT INTO Bookings(UserId, CarId, StartDateTime, PickupPosition, EndDateTime, DropPosition)
VALUES (1, @car_c, '2019-07-01 17:30', POINT(3,3), '2019-07-01 18:30', POINT(3,1));

Insert Into CarLocationHistories(CarId, DateTime, Location) VALUES
(@car_a, '2019-06-30', POINT(1,3)),
(@car_b, '2019-06-30', POINT(1,1)),
(@car_c, '2019-06-30', POINT(1,1)),
(@car_d, '2019-06-30', POINT(0,4));