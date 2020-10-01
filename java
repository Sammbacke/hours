import java.io.*;
import java.util.*;
class Main {
  public static void main(String[] args)throws IOException {
    //List of arrays being used
    ArrayList ideNum = new ArrayList<String>();
    ArrayList name = new ArrayList<String>();
    ArrayList rate = new ArrayList<String>();
    List<Double> pay = new ArrayList<Double>(20);
    ArrayList nameCopy = new ArrayList<String>();
    List<Double> payCopy = new ArrayList<Double>(20);
    String [] splitted;

   

    File file1 = new File("personal data");
    Scanner reader1 = new Scanner(file1);
    fillArray(reader1,ideNum,name,rate);
    
    //read in from payroll file
    File file2 = new File("payroll data");
    Scanner reader2 = new Scanner(file2);

    int i = 0;
    int identity = 1;
    //compare id values and calculate total
    while(reader2.hasNext()){
            
      int num = Integer.parseInt(reader2.nextLine());

          
          //find ideNum that equals the payroll data
          if(identity==num){
            String currentRate = (String)rate.get(i);
            String [] crack = currentRate.split("\\s+");
            double rate1 = Double.parseDouble(crack[0]);
            double rate2 = Double.parseDouble(crack[1]);
            double rate3 = Double.parseDouble(crack[2]);
            // find hours worked
            int shift = reader2.nextInt();
            //calculate value for one shift
            if(shift==1){
              reader2.nextLine();
              String amount = reader2.nextLine();
              splitted = amount.split("\\s+");
              
              double time = findTime(splitted[0],splitted[1]);
              
              double total = (time*rate1)/60;
              pay.add(total);
              
            }
            // calculate value for 2 shifts
            else if(shift==2){
              reader2.nextLine();
              String amount = reader2.nextLine();
              
              splitted = amount.split("\\s+");
              double am = findTime(splitted[0],splitted[1]);
              String amount2 = reader2.nextLine();
              
              splitted = amount2.split("\\s+");
              double am2 = findTime(splitted[0],splitted[1]);

              double total = ((am*rate1) +(am2*rate2))/60;
              pay.add(total);
            }
            //calculate value for 3 shifts
            else if(shift == 3){
              reader2.nextLine();
              String amount = reader2.nextLine();
              splitted = amount.split("\\s+");
              double am = findTime(splitted[0],splitted[1]);
              String amount2 = reader2.nextLine();
              splitted = amount2.split("\\s+");
              double am2 = findTime(splitted[0],splitted[1]);
              String amount3 = reader2.nextLine();
              splitted = amount3.split("\\s+");
              double am3 = findTime(splitted[0],splitted[1]);
              
              double total = ((am*rate1)+ (am2*rate2)+ (am3*rate3))/60;
              pay.add(total);
            }
          }
        
        i = i+1;
        identity++;
        //finds if there is a blank skip and skips it if there is
      if(reader2.hasNext()){
        reader2.nextLine();
      }
        
       
    }


//copies the elements of name
for(int j = 0;j<name.size();j++){
  nameCopy.add(j,name.get(j));
}
//copy elements of pay
for(int h = 0;h<pay.size();h++){
  payCopy.add(h, pay.get(h));
}
//sort name alphabetical
Collections.sort(name);
//sort pay based on name
for(int k = 0;k<pay.size();k++){
  for(int l = 0;l<pay.size();l++){
    if(name.get(k)==nameCopy.get(l)){
      pay.set(k, payCopy.get(l));
    }
  }
}
//print to result
PrintWriter fwrite = new PrintWriter("result");
  for(int n = 0;n<name.size();n++){
  fwrite.println(name.get(n) + " " + pay.get(n));
}
  fwrite.close();  
    }
    
  //go through file for personal data
  public static void fillArray(Scanner in, ArrayList id, ArrayList na, ArrayList ra){ 
      while(in.hasNext()){
        String indent = in.nextLine();
        if(indent.length()>0){
          id.add(indent);
          na.add(in.nextLine());
          in.nextLine();
          in.nextLine();
          ra.add(in.nextLine());
          }
          }
        }
  //onvert the time to minutes calculate
  public static double findTime(String num, String num2){
      String [] cut = num.split(":");
      String [] chop = num2.split(":");
      int hour = Integer.parseInt(cut[0])*60;
      
      int min = Integer.parseInt(cut[1]);
     
      double convert = hour+min;
      
      int hour2 = Integer.parseInt(chop[0])*60;
      
      int min2 = Integer.parseInt(chop[1]);
      double convert2 = hour2+min2;
    
      double total;
      
      if(convert>convert2){
        total = convert+convert2;
      

      }
      else{
        total = convert2-convert;
      }
      
      return total;

    }
  }
