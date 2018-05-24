import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Failas {
static FileWriter fileWriter;
    public static void Irasymas(ArrayList<String> grupes, Map<String, ArrayList<String>> studentai) throws IOException {
        try {
            fileWriter = new FileWriter("dBaze.txt", false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Irasymas ivyko");
        ArrayList<String> listas;
        for(String i : grupes)
        {
            listas = studentai.get(i);
            if(listas != null)
            {
                for (String j : listas)
                {
                    fileWriter.write(i);
                    fileWriter.flush();
                    fileWriter.write(" ");
                    fileWriter.flush();
                    fileWriter.write(j);
                    fileWriter.flush();
                    fileWriter.write("\n");
                    fileWriter.flush();
                }
            }

        }
        fileWriter.close();
    }
    public static void Nuskaitymas(ArrayList<String> grupes, Map<String, ArrayList<String>> studentai, Map<String, ArrayList<String>> datos, Scanner sc)
    {
        System.out.println("Nuskaitymas ivyko");
        boolean check = false;
        while (sc.hasNext()) {
            String a = sc.next();

            String vardas = sc.next();
            String pavarde = sc.next();
            ArrayList<String> list;
            ArrayList<String> list2 = new ArrayList<String>();
            datos.put(vardas+" "+pavarde, list2);
            if(studentai.containsKey(a))
            {
                list = studentai.get(a);
                list.add(vardas+" "+pavarde);
                studentai.put(a, list);
            }
            else {
                list = new ArrayList<String>();
                list.add(vardas+" "+pavarde);
                studentai.put(a,list);
                grupes.add(a);
            }

        }
    }
    public static void Irasymas(Map<String, ArrayList<String>> datos, Map<String, ArrayList<String>> studentai, ArrayList<String> grupes) throws IOException {
        try {
            fileWriter = new FileWriter("dBaze2.txt", false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Irasymas ivyko");
        ArrayList<String> listas = null;
        ArrayList<String> listas2;
        for(String i : grupes)
        {
            if(i != null)
                listas = studentai.get(i);
            if(listas != null)
            {
                    for (String j : listas)
                    {
                        listas2 = datos.get(j);
                        for(String x : listas2)
                        {
                            fileWriter.write(j);
                            fileWriter.flush();
                            fileWriter.write(" ");
                            fileWriter.flush();
                            fileWriter.write(x);
                            fileWriter.flush();
                            fileWriter.write("\n");
                            fileWriter.flush();
                        }
                    }
            }

        }
        fileWriter.close();
    }
    public static void Nuskaitymas(Map<String, ArrayList<String>> datos, Scanner sc2)
    {
        while (sc2.hasNext())
        {
            String vardas = sc2.next();
            String pavarde = sc2.next();
            String metai = sc2.next();
            String men = sc2.next();
            String dienos = sc2.next();
            ArrayList<String> list = datos.get(vardas+" "+pavarde);
            list.add(metai + " " + men + " " + dienos);
            datos.put(vardas+" "+pavarde, list);
        }
    }


}

