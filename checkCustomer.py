customerScore = {"Charaf": 0, "Amine": 710}
command = ""


while True:
    command = input("\nWhat would you like to do? "
    "(F)ind customer (D)elete customer (U)pdate score ")
    if command.lower() == "f":
        customer = input("Please enter the name of the customer.")
        try:
                if customer in customerScore:
                    print("The customer exists.") 
                    if customerScore[customer] > 0:
                        print(f'By the way, you owe us money. ${customerScore[customer]} to be exact.')
                else:
                    raise ValueError ("Customer not found in DB.")
                

        except  ValueError as e:
                print(e)
                print("customer added to DB")
                customerScore[customer] = 0
    if command.lower() == "d":
        customer = input("Please enter the name of the customer you would like to delete. ")
        del(customerScore[customer])
    if command.lower() == "u":
         customer = input("Please enter the name of the customer you wish to update their score. ")
         score = input("What is the score? ")
         customerScore.update({customer: score})
         
    print(customerScore)


