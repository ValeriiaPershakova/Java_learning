package demo;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo {
    public static void main(String[] args) {
        String filePath = new File("").getAbsolutePath();
        System.out.println(filePath);
        File file = new File(filePath.concat("\\rsc\\resources.txt"));
        List<String> lines = new ArrayList<>();
        try (BufferedReader buf = new BufferedReader(new FileReader(file))) {
            int i = 0;
            StringBuilder temp = new StringBuilder();
            while (buf.ready()) {
                String line = buf.readLine();
                if (line.contains("***")) {
                    lines.add(i, temp.toString());
                    i++;
                    temp.setLength(0);
                    continue;
                }
                line.replaceAll("\n", "");
                temp.append(line);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Map> initObjects = new ArrayList<>();


        for (String obj : lines) {
            Map<String, String> params = getMapOfParams();

            params.put("objectType", getInitObjectType(obj));
            if (params.get("objectType") != null) {
                setInitParameters(obj, params);
                initObjects.add(params);
            }
        }
        for (Map<String, String> map : initObjects) {
            System.out.println(map.entrySet());
        }


    }

    private static Map<String, String> getMapOfParams() {
        HashMap<String, String> params = new HashMap<>();
        params.put("objectType", null);
        params.put("bookPhysicType", null);
        params.put("name", null);
        params.put("lastName", null);
        params.put("year", null);
        params.put("bookType", null);
        params.put("paint", null);
        params.put("totalPage", null);
        params.put("authors", null);
        params.put("books", null);
        return params;
    }

    private static String getInitObjectType(String obj) {
        Pattern pattern = Pattern.compile("^Book|Author");
        Matcher matcher = pattern.matcher(obj);
        if (matcher.find()) {
            return matcher.group(0);
        } else return null;
    }

    private static void setInitParameters(String obj, Map<String, String> params) {
        for (String key : params.keySet()) {
            Pattern pattern = Pattern.compile(key + "=");
            Matcher matcher = pattern.matcher(obj);
            if (matcher.find()) {
                pattern = Pattern.compile("[\\w\\s]+");
                Matcher innerMatcher = pattern.matcher(obj);
                if (innerMatcher.find(matcher.end())) {
                    params.replace(key, innerMatcher.group(0));
                }
            }
        }
    }
}
