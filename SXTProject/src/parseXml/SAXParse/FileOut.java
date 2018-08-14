package parseXml.SAXParse;

import java.io.*;

public class FileOut {
    public static void main(String[] args) throws IOException {
        File file = new File("parseXml/DomParse/dom_demo_03.xml");
        FileInputStream fos = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fos));
        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
        fos.close();

    }
}
