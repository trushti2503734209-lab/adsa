def LCS_LENGTH(X, Y):
    m = len(X)
    n = len(Y)

    # Create DP table
    dp = [[0 for j in range(n + 1)] for i in range(m + 1)]

    for i in range(1, m + 1):
        for j in range(1, n + 1):
            if X[i - 1] == Y[j - 1]:
                dp[i][j] = dp[i - 1][j - 1] + 1
            else:
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])

    return dp[m][n], dp


def BUILD_LCS(X, Y, dp):
    i = len(X)
    j = len(Y)
    result = []

    while i > 0 and j > 0:
        if X[i - 1] == Y[j - 1]:
            result.append(X[i - 1])
            i -= 1
            j -= 1
        elif dp[i - 1][j] >= dp[i][j - 1]:
            i -= 1
        else:
            j -= 1

    result.reverse()
    return ''.join(result)


# Main program
X = input("Enter first string: ")
Y = input("Enter second string: ")

length, dp = LCS_LENGTH(X, Y)
lcs = BUILD_LCS(X, Y, dp)

print("Length of LCS:", length)
print("LCS:", lcs)