object CollatzCalculator {
    fun computeStepCount(start: Int): Int {
        require(start >0){"Only positive integers are allowed"}
        var stepsTaken =0
        var number=start
        while(number > 1){
           if( number % 2==0 ){
             number =number/2
        } else{
            number = (number*3)+1
        } 
           stepsTaken++
        }
        return stepsTaken;
    }
}
