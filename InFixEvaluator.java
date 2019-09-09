import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by Utkarsh on 8/30/18.
 */


public class InFixEvaluator

{
    Stack stckA = new Stack(100);
    Stack stckB = new Stack(100);
    Stack track_stck = new Stack(100);
    String operator, value;
    int flag4=0;

    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    public double evaluator(String str) throws Exception {
        if (str.equals("")) {

            throw new NullPointerException("Invalid Expression");

        }

        String splitarray[] = str.split(" ");

        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(splitarray));

        for(int i=0;i<arrayList.size();i++)
        { if(arrayList.get(i).equals("")) {
            arrayList.remove(i);
        i--; }

        }



        for (int i = 0; i < arrayList.size() ; i++) {
            if(arrayList.get(i).equals(""))
            continue;
            if (!arrayList.get(i).equals("+") && !arrayList.get(i).equals("-") && !arrayList.get(i).equals("*") &&
                    !arrayList.get(i).equals("/") && !arrayList.get(i).equals("^") && !arrayList.get(i).equals("sin") &&
                    !arrayList.get(i).equals("cos") && !arrayList.get(i).equals("log") && !arrayList.get(i).equals("(") &&
                    !arrayList.get(i).equals(")") && !arrayList.get(i).equals("{") && !arrayList.get(i).equals("}") &&
                    !arrayList.get(i).equals("[") && !arrayList.get(i).equals("]") && !isDouble(arrayList.get(i))) {
                throw new Exception("Invalid Expression");

            }

        }

        for (int i = 0; i <= arrayList.size() - 2 ; i++) {
            if(arrayList.get(i).equals(""))
                continue;
            if ((isDouble(arrayList.get(i)) && isDouble(arrayList.get(i + 1))))
                throw new Exception("Invalid Expression");

        }

        for (int i = 0; i <= arrayList.size() - 2 ; i++) {
            if(arrayList.get(i).equals(""))
                continue;
            if ((arrayList.get(i).equals("(") && arrayList.get(i+1).equals(")")))
                throw new Exception("Invalid Expression");

        }
        for (int i = 0; i <= arrayList.size() - 2 ; i++) {
            if(arrayList.get(i).equals(""))
                continue;
            if ((arrayList.get(i).equals("{") && arrayList.get(i+1).equals("}")))
                throw new Exception("Invalid Expression");

        }
        for (int i = 0; i <= arrayList.size() - 2 ; i++) {
            if(arrayList.get(i).equals(""))
                continue;
            if ((arrayList.get(i).equals("[") && arrayList.get(i+1).equals("]")))
                throw new Exception("Invalid Expression");

        }


        /*for (int i = 0; i <= arrayList.size() - 2 ; i++)


        {
            if(arrayList.get(i).equals(""))
                continue;

            if (arrayList.get(i).equals("+") || arrayList.get(i).equals("-") || arrayList.get(i).equals("*") ||
                    arrayList.get(i).equals("/") || arrayList.get(i).equals("^") || arrayList.get(i).equals("log") ||
                    arrayList.get(i).equals("sin") && arrayList.get(i).equals("cos"))

            {
                if (arrayList.get(i+1).equals("+") || arrayList.get(i+1).equals("-") || arrayList.get(i+1).equals("*") ||
                        arrayList.get(i+1).equals("/") || arrayList.get(i+1).equals("^") || arrayList.get(i+1).equals("log") ||
                        arrayList.get(i+1).equals("sin") && arrayList.get(i+1).equals("cos"))

                {
                    if (!isDouble(arrayList.get(i)) && !isDouble(arrayList.get(i+1)))

                        throw new Exception("Invalid Expression");
                }

            }
        }

*/

        for (int i = 0; i <= arrayList.size() - 2 ; i++)
        {
            if (arrayList.get(i).equals("+") || arrayList.get(i).equals("-") || arrayList.get(i).equals("*") ||
                    arrayList.get(i).equals("/") || arrayList.get(i).equals("^") )
            {
                if(arrayList.get(i+1).equals("+") || arrayList.get(i+1).equals("-") || arrayList.get(i+1).equals("*") ||
                        arrayList.get(i+1).equals("/") || arrayList.get(i+1).equals("^") )
                { throw new Exception("Invalid Expression");

                }
            }


        }

        for (int i = 0; i <= arrayList.size() - 2 ; i++)
        {
            if (arrayList.get(i).equals("sin") || arrayList.get(i).equals("log") || arrayList.get(i).equals("cos") )
            {
                if (arrayList.get(i+1).equals("+") || arrayList.get(i+1).equals("-") || arrayList.get(i+1).equals("*") ||
                        arrayList.get(i+1).equals("/") || arrayList.get(i+1).equals("^") || arrayList.get(i+1).equals("log") ||
                        arrayList.get(i+1).equals("sin") && arrayList.get(i+1).equals("cos"))

                throw new Exception("Invalid Expression");


            }


        }

        String temp[] = new String[100];

        int top = 0;
        int flag = 0;

        for (int i = 0; i < arrayList.size() ; i++) {
            if(arrayList.get(i).equals(""))
                continue;
            if (!arrayList.get(i).equals("[") && !arrayList.get(i).equals("]") &&
                    !arrayList.get(i).equals("(") && !arrayList.get(i).equals(")") && !arrayList.get(i).equals("{") &&
                    !arrayList.get(i).equals("}"))
                continue;

            if (arrayList.get(i).equals("[") || arrayList.get(i).equals("{") || arrayList.get(i).equals("(")) {
                temp[top] = arrayList.get(i);
                top++;
            } else if (arrayList.get(i).equals("}") && temp[top - 1].equals("{")) {
                flag++;
                top--;
            } else if (arrayList.get(i).equals("]") && temp[top - 1].equals("[")) {
                flag++;
                top--;
            } else if (arrayList.get(i).equals(")") && temp[top - 1].equals("(")) {
                flag++;
                top--;
            } else {
                flag = 0;
                break;
            }
        }


        if (flag == 0 || top>0 )
            throw new Exception("Invalid Expression");


        // return 0.0;
     for(int i=1;i<=arrayList.size()-1 ;i++)
     {
         if(arrayList.get(i).equals(""))
             continue;

         if(arrayList.get(i).equals("+") || arrayList.get(i).equals("-") || arrayList.get(i).equals("*") ||
                 arrayList.get(i).equals("/") || arrayList.get(i).equals("^") )
         {
             if(arrayList.get(i-1).equals("(") || arrayList.get(i-1).equals("[") || arrayList.get(i-1).equals("{")  )
             {

                 if(isDouble(arrayList.get(i+1)))
                     throw new Exception("Invalid Expression");
             }

         }
     }



        for(int i=1;i<=arrayList.size()-1 ;i++)
        {
            if(arrayList.get(i).equals(""))
                continue;
            if(arrayList.get(i).equals("+") || arrayList.get(i).equals("-") || arrayList.get(i).equals("*") ||
                    arrayList.get(i).equals("/") || arrayList.get(i).equals("^") )
            {
                if(isDouble(arrayList.get(i-1)) )
                {
                    if(arrayList.get(i+1).equals(")") || arrayList.get(i+1).equals("]") || arrayList.get(i+1).equals("}")  )
                        throw new Exception("Invalid Expression");

                }
            }
        }




        for(int i=0;i<=arrayList.size()-1 ;i++)
        {
            if(arrayList.get(i).equals(""))
                continue;

            if(arrayList.get(i).equals("log") || arrayList.get(i).equals("cos") || arrayList.get(i).equals("sin") )
            {

                if(arrayList.get(i+1).equals(")") || arrayList.get(i+1).equals("]") || arrayList.get(i+1).equals("}")  )
                {

                        throw new Exception("Invalid Expression");
                }
            }
        }




        double a1 = 0.0, a2 = 0.0, a3 = 0.0;
        int flag1 = 0, flag2 = 0, flag3 = 0;




        for (int i = 0; i < arrayList.size() ; i++) {
            if(arrayList.get(i).equals(""))
                continue;


            if (arrayList.get(i).equals("{") || arrayList.get(i).equals("[") || arrayList.get(i).equals("("))

                stckB.push(arrayList.get(i));


            else if (arrayList.get(i).equals("+") || arrayList.get(i).equals("-") || arrayList.get(i).equals("*") ||
                    arrayList.get(i)
                    .equals("/") || arrayList.get(i).equals("^") || arrayList.get(i).equals("log") || arrayList.get(i).equals
                    ("sin") || arrayList.get(i).equals("cos"))
                stckB.push(arrayList.get(i));


            else if (isDouble(arrayList.get(i)))
                stckA.push(Double.parseDouble(arrayList.get(i)));


            else if (arrayList.get(i).equals("}") || arrayList.get(i).equals("]") || arrayList.get(i).equals(")"))

            {



                    if (stckB.top >= 0)
                        operator = (String) stckB.pop();




                if (operator.equals("{") || operator.equals("[") || operator.equals("("))
                    continue;


                    else if (!operator.equals("sin") && !operator.equals("log") && !operator.equals("cos") &&
                            !operator
                                .equals("")) {


                            if (stckA.top > 0)
                                a1 = (Double) stckA.pop();

                             if (stckA.top >= 0)
                                a2 = (Double) stckA.pop();
                            else
                                throw new Exception("Invalid Expression");

                            if (operator.equals("+"))

                             stckA.push(a2 + a1);



                            else if (operator.equals("-"))

                                stckA.push(a2 - a1);


                            else if (operator.equals("*"))

                                stckA.push(a2 * a1);


                            else if (operator.equals("/")) {
                                if(a1==0 && a2==0)
                                    throw new Exception("NaN");
                                if (a1 == 0 && a2 >= 0)
                                    throw new Exception("Infinity");
                                if (a1 == 0 && a2 < 0)
                                    throw new Exception("-Infinity");

                                stckA.push(a2 / a1);
                            }

                            else if (operator.equals("^"))

                                stckA.push(Math.pow(a2, a1));


                    stckB.pop();
                } else if (operator.equals("sin") || operator.equals("log") || operator.equals("cos")) {

                            if (stckA.top >= 0)
                                a3 = (Double) stckA.pop();
                            else
                                throw new Exception("Invalid Expression");


                            if (operator.equals("sin"))

                                stckA.push(Math.sin(a3));


                            else if (operator.equals("cos"))

                                stckA.push(Math.cos(a3));


                            else if (operator.equals("log"))

                                stckA.push(Math.log(a3));


                        stckB.pop();
                }

                        operator = "";


                    }
                }


                //Write your code here
                // The input comes as a string
                // The final output should be returned as a double.
                // The precision does not matter, as the answers are rounded to the fourth decimal value.
                DecimalFormat df = new DecimalFormat("#.####");


                //Double.parseDouble(df.format((Double) stckA.pop()));     //remove this line and return the

                if(stckA.top>0)
                    throw new Exception("Invalid Expression");

                return (Double) stckA.pop();
                // appropriate answer
            }

        public static void main (String[]args) throws IOException {
            // The buffered reader has been provided.
            // The examples can be found in input.txt file, provided in the src folder.
            // Feel free to add your own examples.
            // Make sure the tests work before submitting your final code.

            InFixEvaluator i = new InFixEvaluator();
            try {
                BufferedReader br = new BufferedReader(new FileReader("src/input.txt"));
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(i.evaluator(line));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


