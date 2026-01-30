import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;



public class study {
    public static void main(String[] args) throws IOException {
        String end = "end";
        String count = "count";
        long allTime = 0;
        LocalDate today = LocalDate.now();
        DateTimeFormatter jdk8 = DateTimeFormatter.ofPattern("M月dd日");
        String fileTime = today.format(jdk8);


        System.out.println();
        while (true) {
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("H.mm");
            System.out.println("请输入开始学习的时间,例如 8.00");
            Scanner sc = new Scanner(System.in);
            String time_Input = sc.next();
            boolean f1 = time_Input.matches("\\d{1,2}\\.\\d{1,2}");
            boolean f2 = time_Input.equals(end);
            boolean f3 = time_Input.equals(count);
            boolean f4 = time_Input.equals("add");
            if (!f1 && !f2 && !f3 && !f4){
                System.out.println("请输入样例中的时间格式 如 12.45");
                continue;
            }
            LocalTime startTime = LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("H:mm")));
            if (f2)
                break;
            if (f3){
                String 记录1 = "今天已经学习了" + allTime + "分钟";
                saveToFile(记录1,fileTime);
                System.out.println(记录1);
                continue;
            }
            if (f4){
                System.out.println("请输入已经学习的时间 (单位分钟)");
                long addTime = sc.nextInt();
                allTime += addTime;
				System.out.println("ok,该时间已经加入今天学习总时间");
				System.out.println();
                continue;
            }
            LocalTime studyStart = LocalTime.parse(time_Input,timeFormat);
            long finalTime = ChronoUnit.MINUTES.between(studyStart,startTime);
            allTime += finalTime;
            String 记录2 = "从  "+studyStart+" 到 "+startTime+" 学习了 "+finalTime+" 分钟";
            System.out.println(记录2);
            saveToFile(记录2,fileTime);
            System.out.println();
        }

        System.out.println();
        System.out.println(today.format(jdk8)+" 一共学习     " + allTime + "    分钟 ");
        String 记录3 = "今天一共学习 " + allTime +" 分钟 ";
        saveToFile(记录3,fileTime);
    }

    private static void saveToFile(String str,String fileTime) throws IOException {
        String path = "C:\\Users\\daton\\Desktop\\"+ fileTime +"学习时间临时记录.txt";

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path,true)));
        bw.write(str);
        bw.newLine();
        bw.flush();
    }
}
