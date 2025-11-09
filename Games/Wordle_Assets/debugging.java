// for (int i = 0; i < 5; i++) {
//                 for (int j = 0; j < 5; j++) {
//                     //Check if the individual answer character is an exact match to word character
//                     if (wordList[i].equals(answerList[i])) {
//                         System.out.print(green + "\u25A0" + clear + " ");
//                         j++;

//                     //Check if atleast the individual answer character is in the word
//                     } else if (wordList[i].contains(answerList[j])){
//                         System.out.print(yellow + "\u25A0" + clear + " ");

//                     }
//                 }
//                 if (word.contains(answerList[i]) != true){
//                 System.out.print(empty + "\u25A0" + clear + " ");
//                 }
//             }
//             System.out.println();


// String[] answerList = answer.split("");
//             String[] wordList = word.split("");
//             int size = 5;
//             int indexToStartAt = 0;
            
//             for (int i = 0; i < 5; i++) {
//                 String[] newArr = new String[size];
//                 System.arraycopy(wordList, indexToStartAt, newArr, 0, size - 1);
                
//                 //Check if the individual answer character is an exact match to word character
//                 if (wordList[i].compareTo(answerList[i]) == 0) {
//                     System.out.print(green);

//                 //Check if atleast the individual answer character is in the word
//                 } else if (word.contains(answerList[i])){
//                     boolean breaker = false;

//                     for (int j = 0; j < size; j++) {
//                         for (int k = 0; k < 5; k++){
//                             if (answerList[k].equals(newArr[j])){
//                                 System.out.print(yellow);
//                                 breaker = true;
//                                 break;
//                             }
//                         }

//                         if (breaker == true){
//                             break;
//                         }
//                     }

//                 } else {
//                     System.out.print(empty);
//                 }
//                 size--;
//                 indexToStartAt++;
//             }
//             System.out.println();
    