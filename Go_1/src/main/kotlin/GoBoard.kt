import java.util.*

class GoBoard(val array: Array<CharArray>) {
   init{
      var size = array[0].size
      array.forEach {
         if(it.size!=size) error("Go board array is not even")
      }
   }

   fun placePiece(index1: Int, index2: Int, type: Char){
      if(type!= 'w' && type != 'b') error("Piece type invalid")
      if(index1 >= array.size || index1<0) error("Index1 invalid")
      if(index2 >= array[0].size || index2<0) error("Index2 invalid")
      array[index1][index2] = type
      val enclosed = getEnclosedCount(index1, index2, type)
      println("$enclosed enclosed")
   }

   fun inBounds(index1: Int, index2: Int): Boolean{
      return (index1>=0 && index1 < array.size) && (index2>=0 && index2 < array[0].size)
   }

   fun getEnclosedCount(index1: Int, index2: Int, type: Char): Int{
      var count = 0
      if(index1 > 0){
         count += getEnclosedFrom(index1-1, index2, type)
      }
      if(index2 > 0){
         count += getEnclosedFrom(index1, index2-1, type)
      }
      if((index1 + 1)<array.size){
         count += getEnclosedFrom(index1+1, index2, type)
      }
      if((index2 + 1)<array[0].size){
         count += getEnclosedFrom(index1, index2+1, type)
      }
      return count
   }

   private fun getEnclosedFrom(index1: Int, index2: Int, type: Char ): Int{
      val visited = booleanArray()
      if(!inBounds(index1, index2)) return 0
      var count = 0
      val nodesToVisit = Stack<Node>()
      var node: Node? = Node(index1, index2)

      while(node != null){
         if(isEndNode((node))) return 0
         visited[node.index1][node.index2] = true
         val nextNode = nextNode(node, visited, type)
         if(nextNode==null){
            if(nodesToVisit.empty()){
               node = null
            }
            else {
               node = nodesToVisit.pop()
            }
            count++
         }
         else{
            nodesToVisit.push(node)
            node = nextNode
         }
      }
      return count
   }

   private fun isEndNode(node:Node):Boolean{
      if(array[node]=='e') return true
      if(node.index1 == 0 || node.index2 == 0) return true
      if(node.index1 == array.size - 1 || node.index2 == array[0].size - 1) return true
      return false
   }

   private fun nextNode(node: Node, visited: Array<BooleanArray>, type: Char): Node?{
      if(checkLocation(node.index1-1, node.index2, visited, type)) return Node(node.index1-1, node.index2)
      if(checkLocation(node.index1, node.index2-1, visited, type)) return Node(node.index1, node.index2-1)
      if(checkLocation(node.index1+1, node.index2, visited, type)) return Node(node.index1+1, node.index2)
      if(checkLocation(node.index1, node.index2+1, visited, type)) return Node(node.index1, node.index2+1)
      return null
   }

   private fun checkLocation(index1: Int, index2: Int, visited: Array<BooleanArray>, type: Char): Boolean{
      return index1>=0 && index2 >=0
              && !visited[index1][index2]
              && array[index1][index2]!= type
   }

   fun booleanArray(): Array<BooleanArray>{
      return Array<BooleanArray>(array.size){BooleanArray(array[0].size)}
   }
}