
package tictactoe;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author Chandan Kumar
 */

public class TicTacToe extends Application {
    
    static void showAlertMSG(String winner){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
        alert.getDialogPane().getButtonTypes().add(type);
        alert.setContentText(winner+" Wins");
        alert.showAndWait();
    }
    
    static class Move 
    { 
        int row, col; 
    }; 

    static char player = 'x', opponent = 'o'; 
    static Boolean isMovesLeft(char board[][]) 
    { 
        for (int i = 0; i < 3; i++) 
            for (int j = 0; j < 3; j++) 
                if (board[i][j] == '_') 
                    return true; 
        return false; 
    } 

     
    static int evaluate(char b[][]) 
    { 
        
        for (int row = 0; row < 3; row++) 
        { 
            if (b[row][0] == b[row][1] && 
                b[row][1] == b[row][2]) 
            { 
                if (b[row][0] == player) 
                    return +10; 
                else if (b[row][0] == opponent) 
                    return -10; 
            } 
        } 

         
        for (int col = 0; col < 3; col++) 
        { 
            if (b[0][col] == b[1][col] && 
                b[1][col] == b[2][col]) 
            { 
                if (b[0][col] == player) 
                    return +10; 

                else if (b[0][col] == opponent) 
                    return -10; 
            } 
        } 

         
        if (b[0][0] == b[1][1] && b[1][1] == b[2][2]) 
        { 
            if (b[0][0] == player) 
                return +10; 
            else if (b[0][0] == opponent) 
                return -10; 
        } 

        if (b[0][2] == b[1][1] && b[1][1] == b[2][0]) 
        { 
            if (b[0][2] == player) 
                return +10; 
            else if (b[0][2] == opponent) 
                return -10; 
        } 

        
        return 0; 
    } 

    static int minimax(char board[][],  
                    int depth, Boolean isMax) 
    { 
        int score = evaluate(board); 

        // If Maximizer has won the game  
        // return his/her evaluated score 
        if (score == 10) 
            return score; 

        // If Minimizer has won the game  
        // return his/her evaluated score 
        if (score == -10) 
            return score; 

        // If there are no more moves and  
        // no winner then it is a tie 
        if (isMovesLeft(board) == false) 
            return 0; 

        // If this maximizer's move 
        if (isMax) 
        { 
            int best = -1000; 

            // Traverse all cells 
            for (int i = 0; i < 3; i++) 
            { 
                for (int j = 0; j < 3; j++) 
                { 
                    // Check if cell is empty 
                    if (board[i][j]=='_') 
                    { 
                        // Make the move 
                        board[i][j] = player; 

                        // Call minimax recursively and choose 
                        // the maximum value 
                        best = Math.max(best, minimax(board,  
                                        depth + 1, !isMax)); 

                        // Undo the move 
                        board[i][j] = '_'; 
                    } 
                } 
            } 
            return best; 
        } 

        // If this minimizer's move 
        else
        { 
            int best = 1000; 

            // Traverse all cells 
            for (int i = 0; i < 3; i++) 
            { 
                for (int j = 0; j < 3; j++) 
                { 
                    // Check if cell is empty 
                    if (board[i][j] == '_') 
                    { 
                        // Make the move 
                        board[i][j] = opponent; 

                        // Call minimax recursively and choose 
                        // the minimum value 
                        best = Math.min(best, minimax(board,  
                                        depth + 1, !isMax)); 

                        // Undo the move 
                        board[i][j] = '_'; 
                    } 
                } 
            } 
            return best; 
        } 
    } 

    
    static Move findBestMove(char board[][]) 
    { 
        int bestVal = -1000; 
        Move bestMove = new Move(); 
        bestMove.row = -1; 
        bestMove.col = -1; 

        
        for (int i = 0; i < 3; i++) 
        { 
            for (int j = 0; j < 3; j++) 
            { 
                // Check if cell is empty 
                if (board[i][j] == '_') 
                { 
                    // Make the move 
                    board[i][j] = player; 

                    // compute evaluation function for this 
                    // move. 
                    int moveVal = minimax(board, 0, false); 

                    // Undo the move 
                    board[i][j] = '_'; 

                    // If the value of the current move is 
                    // more than the best value, then update 
                    // best/ 
                    if (moveVal > bestVal) 
                    { 
                        bestMove.row = i; 
                        bestMove.col = j; 
                        bestVal = moveVal; 
                    } 
                } 
            } 
        } 

        System.out.printf("The value of the best Move " +  
                                 "is : %d\n\n", bestVal); 

        return bestMove; 
    } 
    
    static void changeState(int r, int c, Button b1, Button b2, Button b3, Button b4, Button b5, Button b6, Button b7,Button b8,Button b9){
        if(r==0 && c==0){ b1.setText("X"); }
        else if(r==0 && c==1){ b2.setText("X"); }
        else if(r==0 && c==2){ b3.setText("X"); }
        else if(r==1 && c==0){ b4.setText("X"); }
        else if(r==1 && c==1){ b5.setText("X"); }
        else if(r==1 && c==2){ b6.setText("X"); }
        else if(r==2 && c==0){ b7.setText("X"); }
        else if(r==2 && c==1){ b8.setText("X"); }
        else if(r==2 && c==2){ b9.setText("X"); }
    }
    
    @Override
    public void start(Stage primaryStage) {
        
        // labels
        Text l1= new Text("WELCOME");
        Text l2= new Text("Enter your name:");
        
        //buttons
        Button bt1= new Button();
        Button bt2= new Button();
        
        bt1.setText("Play vs CPU");
        bt2.setText("Play vs Frnd");
        
        TextField txt1 = new TextField();
        
        
        // GAMEPLAY WITH CPU
        
        bt1.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                
                String n=txt1.getText();
                Button btn1= new Button();  btn1.setMinSize(70, 70);
                Button btn2= new Button();  btn2.setMinSize(70, 70);
                Button btn3= new Button();  btn3.setMinSize(70, 70);
                Button btn4= new Button();  btn4.setMinSize(70, 70);
                Button btn5= new Button();  btn5.setMinSize(70, 70);
                Button btn6= new Button();  btn6.setMinSize(70, 70);
                Button btn7= new Button();  btn7.setMinSize(70, 70);
                Button btn8= new Button();  btn8.setMinSize(70, 70);
                Button btn9= new Button();  btn9.setMinSize(70, 70);
                
                btn1.setText("___");
                btn2.setText("___");
                btn3.setText("___");
                btn4.setText("___");
                btn5.setText("___");
                btn6.setText("___");
                btn7.setText("___");
                btn8.setText("___");
                btn9.setText("___");
                
                
                char board[][] = {{ '_', '_', '_' }, 
                                    { '_', '_', '_' }, 
                                    { '_', '_', '_' }};
                
                int[] c = new int[]{ 0 };
                
                
                btn1.setOnAction(new EventHandler<ActionEvent>(){
                    
                    @Override
                    public void handle(ActionEvent event) {
                        if(c[0]%2!=0){
                            
                            int res=evaluate(board);
                            if(res==10){
                                //Setting the content of the dialog
                                System.out.println("X wins");
                                showAlertMSG("CPU");
                            }
                            else if(res==-10){
                                System.out.println("Y wins");
                                showAlertMSG(n);
                            }
                        }
                        else{
                            btn1.setText("O");
                            board[0][0]='o';
                            
                            /*Move bestMove = findBestMove(board);
                            board[bestMove.row][bestMove.col]='x';
                            changeState(bestMove.row, bestMove.col, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9);*/
                            
                            int res=evaluate(board);
                            if(res==10){
                                //Setting the content of the dialog
                                System.out.println("X wins");
                                showAlertMSG("CPU");
                            }
                            else if(res==-10){
                                System.out.println("Y wins");
                                showAlertMSG(n);
                            }
                            
                            Move bestMove = findBestMove(board);
                            board[bestMove.row][bestMove.col]='x';
                            changeState(bestMove.row, bestMove.col, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9);
                        }
                        
                        c[0]=c[0]+1;
                    }
                    
                });
                
                btn2.setOnAction(new EventHandler<ActionEvent>(){
                    
                    @Override
                    public void handle(ActionEvent event) {
                        if(c[0]%2==0){
                            
                            
                            int res=evaluate(board);
                            if(res==10){
                                //Setting the content of the dialog
                                System.out.println("X wins");
                                showAlertMSG("CPU");
                            }
                            else if(res==-10){
                                System.out.println("Y wins");
                                showAlertMSG(n);
                            }
                        }
                        else{
                            btn2.setText("O");
                            board[0][1]='o';
                            
                            
                            int res=evaluate(board);
                            if(res==10){
                                //Setting the content of the dialog
                                System.out.println("X wins");
                                showAlertMSG("CPU");
                            }
                            else if(res==-10){
                                System.out.println("Y wins");
                                showAlertMSG(n);
                            }
                            
                            Move bestMove = findBestMove(board);
                            board[bestMove.row][bestMove.col]='x';
                            changeState(bestMove.row, bestMove.col, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9);
                        }
                        
                    }
                    
                });
                
                btn3.setOnAction(new EventHandler<ActionEvent>(){
                    
                    @Override
                    public void handle(ActionEvent event) {
                        if(c[0]%2==0){
                            
                            
                            int res=evaluate(board);
                            if(res==10){
                                //Setting the content of the dialog
                                System.out.println("X wins");
                                showAlertMSG("CPU");
                            }
                            else if(res==-10){
                                System.out.println("Y wins");
                                showAlertMSG(n);
                            }
                        }
                        else{
                            btn3.setText("O");
                            board[0][2]='o';
                            
                            int res=evaluate(board);
                            if(res==10){
                                //Setting the content of the dialog
                                System.out.println("X wins");
                                showAlertMSG("CPU");
                            }
                            else if(res==-10){
                                System.out.println("Y wins");
                                showAlertMSG(n);
                            }
                            Move bestMove = findBestMove(board);
                            board[bestMove.row][bestMove.col]='x';
                            changeState(bestMove.row, bestMove.col, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9);
                        }
                        c[0]=c[0]+1;
                    }
                    
                });
                
                btn4.setOnAction(new EventHandler<ActionEvent>(){
                    
                    @Override
                    public void handle(ActionEvent event) {
                        if(c[0]%2==0){
                            
                            
                            int res=evaluate(board);
                            if(res==10){
                                //Setting the content of the dialog
                                System.out.println("X wins");
                                showAlertMSG("CPU");
                            }
                            else if(res==-10){
                                System.out.println("Y wins");
                                showAlertMSG(n);
                            }
                        }
                        else{
                            btn4.setText("O");
                            board[1][0]='o';
                            
                            int res=evaluate(board);
                            if(res==10){
                                //Setting the content of the dialog
                                System.out.println("X wins");
                                showAlertMSG("CPU");
                            }
                            else if(res==-10){
                                System.out.println("Y wins");
                                showAlertMSG(n);
                            }
                            Move bestMove = findBestMove(board);
                            board[bestMove.row][bestMove.col]='x';
                            changeState(bestMove.row, bestMove.col, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9);
                        }
                        c[0]=c[0]+1;
                    }
                    
                });
                
                btn5.setOnAction(new EventHandler<ActionEvent>(){
                    
                    @Override
                    public void handle(ActionEvent event) {
                        if(c[0]%2==0){
                            
                            
                            int res=evaluate(board);
                            if(res==10){
                                //Setting the content of the dialog
                                System.out.println("X wins");
                                showAlertMSG("CPU");
                            }
                            else if(res==-10){
                                System.out.println("Y wins");
                                showAlertMSG(n);
                            }
                        }
                        else{
                            btn5.setText("O");
                            board[1][1]='o';
                            
                            int res=evaluate(board);
                            if(res==10){
                                //Setting the content of the dialog
                                System.out.println("X wins");
                                showAlertMSG("CPU");
                            }
                            else if(res==-10){
                                System.out.println("Y wins");
                                showAlertMSG(n);
                            }
                            Move bestMove = findBestMove(board);
                            board[bestMove.row][bestMove.col]='x';
                            changeState(bestMove.row, bestMove.col, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9);
                        }
                        c[0]=c[0]+1;
                    }
                    
                });
                
                btn6.setOnAction(new EventHandler<ActionEvent>(){
                    
                    @Override
                    public void handle(ActionEvent event) {
                        if(c[0]%2==0){
                            
                            
                            int res=evaluate(board);
                            if(res==10){
                                //Setting the content of the dialog
                                System.out.println("X wins");
                                showAlertMSG("CPU");
                            }
                            else if(res==-10){
                                System.out.println("Y wins");
                                showAlertMSG(n);
                            }
                        }
                        else{
                            btn6.setText("O");
                            board[1][2]='o';
                            
                            int res=evaluate(board);
                            if(res==10){
                                //Setting the content of the dialog
                                System.out.println("X wins");
                                showAlertMSG("CPU");
                            }
                            else if(res==-10){
                                System.out.println("Y wins");
                                showAlertMSG(n);
                            }
                            Move bestMove = findBestMove(board);
                            board[bestMove.row][bestMove.col]='x';
                            changeState(bestMove.row, bestMove.col, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9);
                        }
                        c[0]=c[0]+1;
                    }
                    
                });
                
                btn7.setOnAction(new EventHandler<ActionEvent>(){
                    
                    @Override
                    public void handle(ActionEvent event) {
                        if(c[0]%2==0){
                            
                            
                            int res=evaluate(board);
                            if(res==10){
                                //Setting the content of the dialog
                                System.out.println("X wins");
                                showAlertMSG("CPU");
                            }
                            else if(res==-10){
                                System.out.println("Y wins");
                                showAlertMSG(n);
                            }
                        }
                        else{
                            btn7.setText("O");
                            board[2][0]='o';
                            
                            int res=evaluate(board);
                            if(res==10){
                                //Setting the content of the dialog
                                System.out.println("X wins");
                                showAlertMSG("CPU");
                            }
                            else if(res==-10){
                                System.out.println("Y wins");
                                showAlertMSG(n);
                            }
                            Move bestMove = findBestMove(board);
                            board[bestMove.row][bestMove.col]='x';
                            changeState(bestMove.row, bestMove.col, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9);
                        }
                        c[0]=c[0]+1;
                    }
                    
                });
                
                btn8.setOnAction(new EventHandler<ActionEvent>(){
                    
                    @Override
                    public void handle(ActionEvent event) {
                        if(c[0]%2==0){
                            
                            
                            int res=evaluate(board);
                            if(res==10){
                                //Setting the content of the dialog
                                System.out.println("X wins");
                                showAlertMSG("CPU");
                            }
                            else if(res==-10){
                                System.out.println("Y wins");
                                showAlertMSG(n);
                            }
                        }
                        else{
                            btn8.setText("O");
                            board[2][1]='o';
                            
                            int res=evaluate(board);
                            if(res==10){
                                //Setting the content of the dialog
                                System.out.println("X wins");
                                showAlertMSG("CPU");
                            }
                            else if(res==-10){
                                System.out.println("Y wins");
                                showAlertMSG(n);
                            }
                            Move bestMove = findBestMove(board);
                            board[bestMove.row][bestMove.col]='x';
                            changeState(bestMove.row, bestMove.col, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9);
                        }
                        c[0]=c[0]+1;
                    }
                    
                });
                
                btn9.setOnAction(new EventHandler<ActionEvent>(){
                    
                    @Override
                    public void handle(ActionEvent event) {
                        if(c[0]%2==0){
                            
                            
                            int res=evaluate(board);
                            if(res==10){
                                //Setting the content of the dialog
                                System.out.println("X wins");
                                showAlertMSG("CPU");
                            }
                            else if(res==-10){
                                System.out.println("Y wins");
                                showAlertMSG(n);
                            }
                        }
                        else{
                            btn9.setText("O");
                            board[2][2]='o';
                            
                            int res=evaluate(board);
                            if(res==10){
                                //Setting the content of the dialog
                                System.out.println("X wins");
                                showAlertMSG("CPU");
                            }
                            else if(res==-10){
                                System.out.println("Y wins");
                                showAlertMSG(n);
                            }
                            Move bestMove = findBestMove(board);
                            board[bestMove.row][bestMove.col]='x';
                            changeState(bestMove.row, bestMove.col, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9);
                        }
                        c[0]=c[0]+1;
                    }
                    
                });
                
                
                GridPane gridPane = new GridPane();
                gridPane.setMinSize(700, 700);
                gridPane.setVgap(10); 
                gridPane.setHgap(10);
                gridPane.setPadding(new Insets(30, 10, 10, 30));
                
                gridPane.add(btn1, 0, 0);
                gridPane.add(btn2, 1, 0);
                gridPane.add(btn3, 2, 0);
                gridPane.add(btn4, 0, 1);
                gridPane.add(btn5, 1, 1);
                gridPane.add(btn6, 2, 1);
                gridPane.add(btn7, 0, 2);
                gridPane.add(btn8, 1, 2);
                gridPane.add(btn9, 2, 2);
                
                
                

                Scene scene1 = new Scene(gridPane, 300, 300);
                primaryStage.setScene(scene1);
                primaryStage.show();
            }
            
        });
        
        
        // GAMEPLAY WITH FRIEND
        
        bt2.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                
                Button btn1= new Button();  btn1.setMinSize(70, 70);
                Button btn2= new Button();  btn2.setMinSize(70, 70);
                Button btn3= new Button();  btn3.setMinSize(70, 70);
                Button btn4= new Button();  btn4.setMinSize(70, 70);
                Button btn5= new Button();  btn5.setMinSize(70, 70);
                Button btn6= new Button();  btn6.setMinSize(70, 70);
                Button btn7= new Button();  btn7.setMinSize(70, 70);
                Button btn8= new Button();  btn8.setMinSize(70, 70);
                Button btn9= new Button();  btn9.setMinSize(70, 70);
                
                btn1.setText("___");
                btn2.setText("___");
                btn3.setText("___");
                btn4.setText("___");
                btn5.setText("___");
                btn6.setText("___");
                btn7.setText("___");
                btn8.setText("___");
                btn9.setText("___");
                
                
                char board[][] = {{ '_', '_', '_' }, 
                                    { '_', '_', '_' }, 
                                    { '_', '_', '_' }};
                
                int[] c = new int[]{ 0 };
                
                
                btn1.setOnAction(new EventHandler<ActionEvent>(){
                    
                    @Override
                    public void handle(ActionEvent event) {
                        if(c[0]%2==0){
                            
                           btn1.setText("X");
                            board[0][0]='x';
                            int res=evaluate(board);
                            if(res==10){
                                //Setting the content of the dialog
                                System.out.println("X wins");
                                showAlertMSG("X");
                            }
                            else if(res==-10){
                                System.out.println("Y wins");
                                showAlertMSG("Y");
                            }
                        }
                        else{
                            btn1.setText("O");
                            board[0][0]='o';
                            
                            int res=evaluate(board);
                            if(res==10){
                                //Setting the content of the dialog
                                System.out.println("X wins");
                                showAlertMSG("X");
                            }
                            else if(res==-10){
                                System.out.println("Y wins");
                                showAlertMSG("Y");
                            }
                        }
                        c[0]=c[0]+1;
                    }
                    
                });
                
                btn2.setOnAction(new EventHandler<ActionEvent>(){
                    
                    @Override
                    public void handle(ActionEvent event) {
                        if(c[0]%2==0){
                            
                            btn2.setText("X");
                            board[0][1]='x';
                            int res=evaluate(board);
                            if(res==10){
                                //Setting the content of the dialog
                                System.out.println("X wins");
                                showAlertMSG("X");
                            }
                            else if(res==-10){
                                System.out.println("Y wins");
                                showAlertMSG("Y");
                            }
                        }
                        else{
                            btn2.setText("O");
                            board[0][1]='o';
                            
                            int res=evaluate(board);
                            if(res==10){
                                //Setting the content of the dialog
                                System.out.println("X wins");
                                showAlertMSG("X");
                            }
                            else if(res==-10){
                                System.out.println("Y wins");
                                showAlertMSG("Y");
                            }
                        }
                        c[0]=c[0]+1;
                    }
                    
                });
                
                btn3.setOnAction(new EventHandler<ActionEvent>(){
                    
                    @Override
                    public void handle(ActionEvent event) {
                        if(c[0]%2==0){
                            
                            btn3.setText("X");
                            board[0][2]='x';
                            int res=evaluate(board);
                            if(res==10){
                                //Setting the content of the dialog
                                System.out.println("X wins");
                                showAlertMSG("X");
                            }
                            else if(res==-10){
                                System.out.println("Y wins");
                                showAlertMSG("Y");
                            }
                        }
                        else{
                            btn3.setText("O");
                            board[0][2]='o';
                            
                            int res=evaluate(board);
                            if(res==10){
                                //Setting the content of the dialog
                                System.out.println("X wins");
                                showAlertMSG("X");
                            }
                            else if(res==-10){
                                System.out.println("Y wins");
                                showAlertMSG("Y");
                            }
                        }
                        c[0]=c[0]+1;
                    }
                    
                });
                
                btn4.setOnAction(new EventHandler<ActionEvent>(){
                    
                    @Override
                    public void handle(ActionEvent event) {
                        if(c[0]%2==0){
                            
                            btn4.setText("X");
                            board[1][0]='x';
                            int res=evaluate(board);
                            if(res==10){
                                //Setting the content of the dialog
                                System.out.println("X wins");
                                showAlertMSG("X");
                            }
                            else if(res==-10){
                                System.out.println("Y wins");
                                showAlertMSG("Y");
                            }
                        }
                        else{
                            btn4.setText("O");
                            board[1][0]='o';
                            
                            int res=evaluate(board);
                            if(res==10){
                                //Setting the content of the dialog
                                System.out.println("X wins");
                                showAlertMSG("X");
                            }
                            else if(res==-10){
                                System.out.println("Y wins");
                                showAlertMSG("Y");
                            }
                        }
                        c[0]=c[0]+1;
                    }
                    
                });
                
                btn5.setOnAction(new EventHandler<ActionEvent>(){
                    
                    @Override
                    public void handle(ActionEvent event) {
                        if(c[0]%2==0){
                            
                            btn5.setText("X");
                            board[1][1]='x';
                            int res=evaluate(board);
                            if(res==10){
                                //Setting the content of the dialog
                                System.out.println("X wins");
                                showAlertMSG("X");
                            }
                            else if(res==-10){
                                System.out.println("Y wins");
                                showAlertMSG("Y");
                            }
                        }
                        else{
                            btn5.setText("O");
                            board[1][1]='o';
                            
                            int res=evaluate(board);
                            if(res==10){
                                //Setting the content of the dialog
                                System.out.println("X wins");
                                showAlertMSG("X");
                            }
                            else if(res==-10){
                                System.out.println("Y wins");
                                showAlertMSG("Y");
                            }
                        }
                        c[0]=c[0]+1;
                    }
                    
                });
                
                btn6.setOnAction(new EventHandler<ActionEvent>(){
                    
                    @Override
                    public void handle(ActionEvent event) {
                        if(c[0]%2==0){
                            
                            btn6.setText("X");
                            board[1][2]='x';
                            int res=evaluate(board);
                            if(res==10){
                                //Setting the content of the dialog
                                System.out.println("X wins");
                                showAlertMSG("X");
                            }
                            else if(res==-10){
                                System.out.println("Y wins");
                                showAlertMSG("Y");
                            }
                        }
                        else{
                            btn6.setText("O");
                            board[1][2]='o';
                            
                            int res=evaluate(board);
                            if(res==10){
                                //Setting the content of the dialog
                                System.out.println("X wins");
                                showAlertMSG("X");
                            }
                            else if(res==-10){
                                System.out.println("Y wins");
                                showAlertMSG("Y");
                            }
                        }
                        c[0]=c[0]+1;
                    }
                    
                });
                
                btn7.setOnAction(new EventHandler<ActionEvent>(){
                    
                    @Override
                    public void handle(ActionEvent event) {
                        if(c[0]%2==0){
                            
                            btn7.setText("X");
                            board[2][0]='x';
                            int res=evaluate(board);
                            if(res==10){
                                //Setting the content of the dialog
                                System.out.println("X wins");
                                showAlertMSG("X");
                            }
                            else if(res==-10){
                                System.out.println("Y wins");
                                showAlertMSG("Y");
                            }
                        }
                        else{
                            btn7.setText("O");
                            board[2][0]='o';
                            
                            int res=evaluate(board);
                            if(res==10){
                                //Setting the content of the dialog
                                System.out.println("X wins");
                                showAlertMSG("X");
                            }
                            else if(res==-10){
                                System.out.println("Y wins");
                                showAlertMSG("Y");
                            }
                        }
                        c[0]=c[0]+1;
                    }
                    
                });
                
                btn8.setOnAction(new EventHandler<ActionEvent>(){
                    
                    @Override
                    public void handle(ActionEvent event) {
                        if(c[0]%2==0){
                            
                            btn8.setText("X");
                            board[2][1]='x';
                            int res=evaluate(board);
                            if(res==10){
                                //Setting the content of the dialog
                                System.out.println("X wins");
                                showAlertMSG("X");
                            }
                            else if(res==-10){
                                System.out.println("Y wins");
                                showAlertMSG("Y");
                            }
                        }
                        else{
                            btn8.setText("O");
                            board[2][1]='o';
                            
                            int res=evaluate(board);
                            if(res==10){
                                //Setting the content of the dialog
                                System.out.println("X wins");
                                showAlertMSG("X");
                            }
                            else if(res==-10){
                                System.out.println("Y wins");
                                showAlertMSG("Y");
                            }
                        }
                        c[0]=c[0]+1;
                    }
                    
                });
                
                btn9.setOnAction(new EventHandler<ActionEvent>(){
                    
                    @Override
                    public void handle(ActionEvent event) {
                        if(c[0]%2==0){
                            
                            btn9.setText("X");
                            board[2][2]='x';
                            int res=evaluate(board);
                            if(res==10){
                                //Setting the content of the dialog
                                System.out.println("X wins");
                                showAlertMSG("X");
                            }
                            else if(res==-10){
                                System.out.println("Y wins");
                                showAlertMSG("Y");
                            }
                        }
                        else{
                            btn9.setText("O");
                            board[2][2]='o';
                            
                            int res=evaluate(board);
                            if(res==10){
                                //Setting the content of the dialog
                                System.out.println("X wins");
                                showAlertMSG("X");
                            }
                            else if(res==-10){
                                System.out.println("Y wins");
                                showAlertMSG("Y");
                            }
                        }
                        c[0]=c[0]+1;
                    }
                    
                });
                
                
                GridPane gridPane = new GridPane();
                gridPane.setMinSize(700, 700);
                gridPane.setVgap(10); 
                gridPane.setHgap(10);
                gridPane.setPadding(new Insets(30, 10, 10, 30));
                
                gridPane.add(btn1, 0, 0);
                gridPane.add(btn2, 1, 0);
                gridPane.add(btn3, 2, 0);
                gridPane.add(btn4, 0, 1);
                gridPane.add(btn5, 1, 1);
                gridPane.add(btn6, 2, 1);
                gridPane.add(btn7, 0, 2);
                gridPane.add(btn8, 1, 2);
                gridPane.add(btn9, 2, 2);
                
                
                

                Scene scene1 = new Scene(gridPane, 300, 300);
                primaryStage.setScene(scene1);
                primaryStage.show();
            }
            
        });
        
        
        // Scene
        primaryStage.setTitle("Tic Tac Toe");
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(300, 300);
        gridPane.setVgap(5); 
        gridPane.setHgap(5);
        gridPane.setPadding(new Insets(30, 10, 10, 30));
        l1.setTextAlignment(TextAlignment.CENTER);
        gridPane.add(l1, 0, 0); 
        gridPane.add(l2, 0, 1);
        gridPane.add(txt1, 1, 1);
        gridPane.add(bt1, 0, 2);
        gridPane.add(bt2, 1, 2);
        
        Scene scene = new Scene(gridPane, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
        
          
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
