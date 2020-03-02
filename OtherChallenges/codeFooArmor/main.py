import csv

#csv is already sorted by cost efficiency (Armor value / Cost in Crowns)
#Can be easily be done using Google Sheets
#Since the input is already ordered with the cost efficiency, all I need to do is find the first occurence of each armor type
#I also check the total crown cost after each piece of armor is added as to not exceed 300 crowns
#To get the extra piece of armor, I check for the next armor piece that is not yet added to the bestArmors array as well as not exceeding 300 crowns if added
#This solution should work with other inventories as long as it is sorted first
#One problem may arise with adding the extra piece of armor if there are multiple armor pieces with the same name (a sample case is with different qualities)
#A quick solution would be to also check the quality type as well as the name

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
            bestArmors.append("|||") #Spacing to make it easier to read
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
