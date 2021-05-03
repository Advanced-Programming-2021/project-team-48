import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProgramControler {
    public static Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }


    public ProgramControler() {
        run();
    }

    public static void run() {
        String input = scanner.nextLine();
        while (!input.equals("menu exit")) {
            boolean checker = false;

            Pattern pattern = Pattern.compile("menu show-current");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                System.out.println("Login Menu");
            }

//ja be jaie hatmn dorost she!!!!!
            pattern = Pattern.compile("user create --username ([^\\s]+) --nickname ([^\\s]+) --password ([^\\s]+)");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                String username = matcher.group(1);
                String nickname = matcher.group(2);
                String password = matcher.group(3);
                creatUser(username, nickname, password);
            }


            pattern = Pattern.compile("user login --username ([^\\s]+) --password ([^\\s]+)");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                String username = matcher.group(1);
                String password = matcher.group(2);
                login(username, password);

            }
//login first---------------------------------------------------------------------

            input = scanner.nextLine();
        }

    }


    private static void creatUser(String username, String nickname, String password) {
        for (User user : User.listOfUsers) {
            if (user.username.equals(username)) {
                System.out.println("user with username " + username + " already exists");
                return;
            }
        }
        for (User user : User.listOfUsers) {
            if (user.nickname.equals(nickname)) {
                System.out.println("user with nickname " + nickname + " already exists");
                return;
            }
        }
        new User(username, nickname, password);
        System.out.println("user created successfully!");
        return;
    }

    private static void login(String username, String password) {
        //check she ke boolean null nishe
        if (!User.passwordChecker(username, password)) {
            System.out.println("Username and password didn’t match!");
        } else {
            mainMenu(User.getUserByUsername(username));
        }
    }


    private static void mainMenu(User user) {
        String input = scanner.nextLine();
        while (!input.equals("menu exit")) {
            boolean checker = false;

            Pattern pattern = Pattern.compile("menu show-current");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                System.out.println("Main Menu");
            }


            pattern = Pattern.compile("user logout");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                System.out.println("user logged out successfully!");
                return;
            }

            pattern = Pattern.compile("menu enter scoreboard");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                scorebosrd();

            }

            pattern = Pattern.compile("menu enter profile");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                profile(user);
            }

            pattern = Pattern.compile("menu enter deck");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                deck(user);
            }


            input = scanner.nextLine();
        }
    }


    public static void scorebosrd() {
        String input = scanner.nextLine();
        while (!input.equals("menu exit")) {
            boolean checker = false;

            Pattern pattern = Pattern.compile("menu show-current");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                System.out.println("Scoreboard Menu");
            }

            pattern = Pattern.compile("scoreboard show");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                ArrayList<User> ordersList = new ArrayList<User>();
                for (User userA : User.listOfUsers) {
                    for (User userB : User.listOfUsers) {

                    }

                }
            }


            input = scanner.nextLine();
        }
    }




    public static void profile(User user) {
        String input = scanner.nextLine();
        while (!input.equals("menu exit")) {
            boolean checker = false;

            Pattern pattern = Pattern.compile("menu show-current");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                System.out.println("profile Menu");
            }


            pattern = Pattern.compile("profile change --nickname (.+)");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;

                String newNickname = matcher.group(1);

                if (user.nickname.equals(newNickname)) {
                    System.out.println("this nickname is same to the nickname you have now!");

                } else {
                    for (User user1 : User.listOfUsers) {
                        if (user1.nickname.equals(newNickname)) {
                            System.out.println("user with nickname " + newNickname + " already exists");
                            break;
                        }
                        user.setNickname(newNickname);
                        System.out.println("nickname changed successfully!");
                    }

                }


                pattern = Pattern.compile("profile change --password --current (.+) --new (.+)");
                matcher = pattern.matcher(input);
                if (matcher.find()) {
                    checker = true;
                    String password = matcher.group(1);
                    String newPassword = matcher.group(2);
                    if (user.getPassword().equals(1)) {
                        if (!password.equals(newPassword)) {
                            user.setPassword(newPassword);
                            System.out.println("password changed successfully!");
                        } else {
                            System.out.println("please enter a new password");
                        }
                    } else {
                        System.out.println("current password is invalid");
                    }
                }


                input = scanner.nextLine();

            }
        }
    }
    public static void deck(User user){





    }
}