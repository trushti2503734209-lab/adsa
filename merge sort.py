 def merge(arr,lb,mid,ud):
    b=[0]*len(arr)
    i=lb
    j=mid+1
    k=lb
    while i<=mid and j<=ub:
        if arr[i]<=arr[j]:
            b[k]=arr[i]
            i+=1
        else:
        b[k]=arr[i]
        j+=1
        k+=1
    while i<=mid:
        b[k]=arr[i]
        i+=1
        k+=1
    while j<=ub:
        b[k]=arr[j]
        j+=1
        k+=1
    for k in range(lb,ub,ub):
        if lb<ub:
            mid=(lb+ub)
            merge_sort(arr,lb,mid)
            merge_sort(arr,lb,mid,ub)
arr=[5,24,8,1,3,16,10,20]
print("original Array:",arr) 
merge_sort(arr,0,len(arr)-1)
print("sorted Array:",arr)
                     
