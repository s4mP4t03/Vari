# Program to check if a number is prime or not

num1 = 1
# define a flag variable
while(num1 != num2):
    # check for factors
        num1 += 1
        flag = False
        for i in range(2, num1):
            if (num1 % i) == 0:
                # if factor is found, set flag to True
                flag = True
                # break out of loop
                break
    
        # check if flag is True
        if not(flag):
            print(num1, "is a prime number")
            
