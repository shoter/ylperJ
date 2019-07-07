Engines = [
    (1, "Diesel"),
    (2, "Gasoline"),
    (3, "Electric")
]

Luxuries = [
    (1, "Cheap"),
    (2, "Standard"),
    (3, "Luxurious")
]

Companies = [
    (1, 'Skoda'),
    (2, 'Volvo'),
    (3, 'Toyota'),
    (4, 'Fiat')
]

def CalculateFuelConsumption(engineId, companyId, luxuryId):
    return 100 + engineId * companyId * luxuryId + engineId

for e in Engines:
    for l in Luxuries:
        for c in Companies:
            carName = l[1] + " " + c[1] + " " + e[1] + " car"
            print "(" + \
            str(c[0]) + "," + \
            str(e[0]) + "," + \
            str(l[0]) + "," + \
            "'" + carName + "'," + \
            str(CalculateFuelConsumption(e[0], c[0], l[0])) + "),"