LOG_FILE1=test_3_3_diagonal_draw_row.log
LOG_FILE2=test_5_4_column_diagonal_row_anothergame.log
LOG_FILE3=test_9_5_diagonal.log

touch $LOG_FILE1
touch $LOG_FILE2
touch $LOG_FILE3
touch $LOG_FILE4
touch $LOG_FILE5

#LOG_FILE1
(echo 2 1 gracz1 2 2 gracz2 1 5 1 3 2 7 y 1 2 3 4 5 9 6 7 8 y 1 5 7 4 3 6 n) | java -jar target/OX.java.academy-2.0.jar | tee ${LOG_FILE1}

#LOG_FILE2
(echo 4 1 2 1 player1 2 2 player2 3 1 5 3 2 4 1 2 3 7 8 12 13 17 18 y 2 7 8 13 14 19 20 y 6 11 7 12 8 13 9 14 y 2 7 8 13 14 19 20 n n) | java -jar target/OX.java.academy-2.0.jar | tee ${LOG_FILE1}

