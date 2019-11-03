

fun main(){

   val goBoard = GoBoard(
           /*arrayOf(
                   charArrayOf('e', 'e', 'e', 'e', 'b', 'b', 'b'),
                   charArrayOf('e', 'e', 'e', 'e', 'b', 'w', 'b'),
                   charArrayOf('e', 'e', 'e', 'e', 'b', 'e', 'b'),
                   charArrayOf('e', 'e', 'e', 'e', 'e', 'e', 'e'))*/
           arrayOf(
                   charArrayOf('e', 'e', 'e', 'e', 'b', 'b', 'b'),
                   charArrayOf('e', 'e', 'e', 'e', 'w', 'w', 'b'),
                   charArrayOf('e', 'e', 'e', 'e', 'b', 'e', 'b'),
                   charArrayOf('e', 'e', 'e', 'e', 'e', 'e', 'e'))
   )

   goBoard.placePiece(2, 5, 'b')
}