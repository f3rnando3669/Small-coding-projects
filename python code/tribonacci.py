class Solution(object):
    def tribonacci(self, n):
        memo = [0,1,1]#initializing the frist 3 elements 
        #checking for the first base cases 
        if n == 0:
            return 0
        elif n == 1 or n == 2:
            return 1
          #clculates the rest of the numbers on the list 
        for i in range (3,n+1):
            memo.append(memo[i-1]+memo[i-2]+memo[i-3])
        return memo[n]