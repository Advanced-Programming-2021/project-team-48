package sample.view.graphic;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.controller.UserLogined;

import javax.swing.text.Element;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Chat extends Application {
    private static Stage stage;

    private Timeline timer;

    @FXML
    private TextField massage;
    @FXML
    private AnchorPane chatBox;
    @FXML
    private Text online;
    public String allMessage = "";
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;


    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Parent parent = FXMLLoader.load(getClass().getResource("Chat.fxml"));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {

        try {
            socket = new Socket("localhost", 7001);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void creatChatBox() {
        try {
            dataOutputStream.writeUTF("getChats");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            allMessage = dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] oneByOne = allMessage.split("/");
        int i = 0;
        for (String msg : oneByOne) {
            i++;
        }
        chatBox.setPrefHeight(i * 180);
        ArrayList<Text> allTexts = new ArrayList<>();
        ArrayList<ImageView> allImage = new ArrayList<>();
        i = 30;
        String usernamrTem = "";
        for (String message : oneByOne) {
            String[] newMeg = message.split(",");

            if (!usernamrTem.equals(newMeg[0])) {
                if (!newMeg[0].equals(UserLogined.user.username)) {
                    try {
                        dataOutputStream.writeUTF("getImage,"+newMeg[0]);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        dataOutputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String imageAddress="";
                    try {
                        imageAddress=dataInputStream.readUTF();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ImageView imageView=new ImageView();
                    imageView.setImage(new Image(new File(imageAddress).toURI().toString()));
                    imageView.setFitHeight(50);
                    imageView.setFitWidth(50);
                    imageView.setX(0);
                    imageView.setY(i-20);
                    imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            ShowUser.user=newMeg[0];
                            try {
                                new ShowUser().start(stage);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    allImage.add(imageView);
                    usernamrTem = newMeg[0];
                }else {
                    try {
                        dataOutputStream.writeUTF("getImage,"+newMeg[0]);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        dataOutputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String imageAddress="";
                    try {
                        imageAddress=dataInputStream.readUTF();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ImageView imageView=new ImageView();
                    imageView.setImage(new Image(new File(imageAddress).toURI().toString()));
                    imageView.setFitHeight(50);
                    imageView.setFitWidth(50);
                    imageView.setX(1400);
                    imageView.setY(i-20);

                    allImage.add(imageView);
                    usernamrTem = newMeg[0];

                }
            }
            if (!newMeg[0].equals(UserLogined.user.username)) {
                Text textMsg = new Text();
                textMsg.setText(newMeg[1]);
                textMsg.setX(50);
                textMsg.setY(i);
                i += 50;
                allTexts.add(textMsg);
            }
            if (newMeg[0].equals(UserLogined.user.username)) {
                Text textMsg = new Text();
                textMsg.setText(newMeg[1]);
                textMsg.setX(1200);
                textMsg.setY(i);
                i += 50;
                allTexts.add(textMsg);
            }

        }
        chatBox.getChildren().addAll(allImage);
        chatBox.getChildren().addAll(allTexts);
    }


    public void SendClicked() {
        DataOutputStream dataOutputStream = null;
        try {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataOutputStream.writeUTF("newMsg," + UserLogined.user.username + "," + massage.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        massage.clear();
        DataOutputStream finalDataOutputStream = dataOutputStream;
        KeyFrame frame = new KeyFrame(Duration.seconds(0.5), event -> {
            chatBox.getChildren().clear();
            creatChatBox();
            String numOfUsers = "";
            try {
                finalDataOutputStream.writeUTF("getNumOfOnlineUsers");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                finalDataOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                numOfUsers = dataInputStream.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            online.setText(numOfUsers);
        });
        this.timer = new Timeline(frame);
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();


    }


    public void Back() throws Exception {
        new MainMenu().start(stage);
    }


}
