import java.util.scanner;
public class optimalstorageontape{
    public static void main(string[]args){
        scanner sc=new scanner(system.in);
        system.out.print("enter number of files:");
        int n=sc.nextInt();
        int[]lengthg=new int[n];
        system.out.println("enter the length of each file:");
        for(int i=0;i<n;i++){
            system.out.print("file"+(i+1)+":");
            length[i]=sc.nextInt();
        }
        for(int i=0;i<n-1;i++){
            for(int j=i+1;j,n;j++){
                int temp=lengt[i];
                length[i]=length[j];
                length[j]=temp;
            }
        }    
    }    
        for(int i=0;i<n-1;i++){
            for(int j=1+1; j<n; j++){
                if(length[i]>length[j]){
                    int temp