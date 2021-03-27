///usr/bin/env jbang "$0" "$@" ; exit $?
//REPOS jcenter, jitpack
//DEPS info.picocli:picocli:4.5.0
//DEPS com.github.gantoin:bitchute-uploader:1.1
//DEPS com.andreapivetta.kolor:kolor:1.0.0

import static java.lang.System.out;

import java.util.Scanner;
import java.util.concurrent.Callable;

import com.andreapivetta.kolor.Color;
import com.andreapivetta.kolor.Kolor;

import domain.BitchuteUpload;
import domain.BitchuteVideo;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "upload", mixinStandardHelpOptions = true, version = "upload 0.1",
        description = "upload made with jbang")
class upload implements Callable<Integer> {

    public static void main(String... args) {

        BitchuteVideo video = new BitchuteVideo();

        int exitCode = new CommandLine(new upload()).execute(args);
        Kolor kolor = Kolor.INSTANCE;

        out.println(kolor.foreground("\n------\nVIDEO INFOS\n-----", Color.RED));
        Scanner scanner = new Scanner(System.in);
        out.println(kolor.foreground("Title:", Color.YELLOW));
        video.setTitle(scanner.nextLine());
        out.println(kolor.foreground("Description:", Color.YELLOW));
        video.setDescription(scanner.nextLine());
        out.println(kolor.foreground("Video file path:", Color.YELLOW));
        video.setVideoPath(scanner.nextLine());
        out.println(kolor.foreground("Cover file path:", Color.YELLOW));
        video.setCoverPath(scanner.nextLine());


        out.println(kolor.foreground("\n-----\nUSER INFOS\n-----", Color.RED));
        out.println(kolor.foreground("Chrome driver path:", Color.YELLOW));
        String chrome = scanner.nextLine();
        out.println(kolor.foreground("User mail:", Color.YELLOW));
        String mail = scanner.nextLine();
        out.println(kolor.foreground("User password:", Color.YELLOW));
        String password = scanner.nextLine();
        out.println(kolor.foreground("Headless mode (true/false):", Color.YELLOW));
        boolean headless = scanner.nextBoolean();

        out.println(kolor.foreground("\n-----\nStart Upload\n-----", Color.RED));
        BitchuteUpload upload = new BitchuteUpload(chrome, mail, password, headless);
        upload.uploadVideo(video);

        out.println(kolor.foreground("\nCongratulations ! Your video is uploaded.\n", Color.BLUE));
        System.exit(exitCode);
    }

    @Override
    public Integer call() {
        return 0;
    }
}
