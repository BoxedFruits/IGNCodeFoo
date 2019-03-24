import csv

#csv is already sorted by cost efficiency (Armor value / Cost in Crowns)
#Can be easily be done using Google Sheets

with open("Witcher_Inventory(Sorted).csv") as csvfile:
    input_file = csv.DictReader(csvfile, delimiter = ",")
    input_files = list(input_file)
    bestArmors = ["Best armors: "]
    maxCrowns = 300
    crownCost = 0

    armorTypes = []
    for row in input_files: # Gets unique armor types
        if row['Armor Type'] not in armorTypes:
            armorTypes.append(row['Armor Type'])

    for row in input_files:
        if(row['Armor Type'] not in bestArmors):
            bestArmors.append(row['Armor Type'])
            bestArmors.append(row['Name'])
            bestArmors.append(row['Cost in Crowns'])
            bestArmors.append(row['Armor value'])
            bestArmors.append(row['Cost Efficiency'])
            bestArmors.append("|||")
            crownCost += int(row['Cost in Crowns'])
        if(crownCost > maxCrowns):
            print("Crown cost is over")
            break

    for row in input_files: # Adding the extra piece of armor
        if(row['Name'] not in bestArmors):
            crownCost += int(row['Cost in Crowns'])
            if (crownCost > maxCrowns): #Will skip item if total crown cost exceeds 300
                print("Crown cost is over")
                crownCost -= int(row['Cost in Crowns'])
                continue
            bestArmors.append(row['Armor Type'])
            bestArmors.append(row['Name'])
            bestArmors.append(row['Cost in Crowns'])
            bestArmors.append(row['Armor value'])
            bestArmors.append(row['Cost Efficiency'])
            bestArmors.append("|||")
            break

    print(armorTypes)
    print(bestArmors)
    print(f"{crownCost} is the crown cost")
