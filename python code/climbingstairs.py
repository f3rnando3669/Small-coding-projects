class Solution(object):
    def climbStairs(self, n):
        #mmoize the first 2 step
        m = [1, 2]  # Initial values for steps 1 and 2
        # covers the first 2 steps
        if n == 1:
            return 1
        if n == 2:
            return 2
        # for the 3rd step and onward we append the rest into the meomize list
        for i in range(2, n):
            #similar to fibonaci sequence 
            m.append(m[i-1] + m[i-2])
        #returns numbner of ways to climb nth steps
        return m[n-1]
    #the time complexity is big O(n) because of memoization elminating 
    #the need to recurse over the repeating proceces, therfore 
    #the overall time complexity is linear in terms of n.