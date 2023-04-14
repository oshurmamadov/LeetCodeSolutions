package algorithm_1

import java.util.*
import java.util.ArrayDeque

class Solution704 {

    fun search(nums: IntArray, target: Int): Int {
        return search(nums, 0, nums.size - 1, target)
    }

    private fun search(nums: IntArray, low: Int, high: Int, target: Int): Int {
        if (low > high) return -1
        val middle = low + (high - low) / 2

        return if (nums[middle] == target) {
            middle
        } else if (target < nums[middle]) {
            search(nums, low, middle - 1, target)
        } else {
            search(nums, middle + 1, high, target)
        }
    }
}

class Solution278 {

    /**
     * An API call which returns some if it is a bad version
     */
    private fun isBadVersion(n: Int) = false

    fun firstBadVersion(n: Int) : Int {
        return firstBadVersion(1, n)
    }

    private fun firstBadVersion(low: Int, high: Int) : Int {
        if (low > high ) return low
        val middle = low + (high - low) / 2


        return if (isBadVersion(middle) == false) {
            firstBadVersion(middle + 1, high)
        } else {
            firstBadVersion(low, middle - 1)
        }
    }
}

class Solution279 {

    fun numSquares(n: Int): Int {
        val res = IntArray(n + 1)
        Arrays.fill(res, Int.MAX_VALUE)
        res[0] = 0
        for (i in 0..n) {
            var j = 1
            while (j * j <= i) {
                val diff = i - j * j
                res[i] = Math.min(res[i], res[diff] + 1)
                j++
            }
        }
        return res[n]
    }
}

class Solution35 {

    fun searchInsert(nums: IntArray, target: Int): Int {
        return find(nums, target, 0, nums.size - 1)
    }

    var middle = 0
    fun find(nums: IntArray, target: Int, low: Int, height: Int): Int {
        if (low > height) return low

        middle = low + (height - low) / 2

        return if (nums[middle] == target) {
            middle
        } else if(target < nums[middle]) {
            find(nums, target, low, middle - 1)
        } else {
            find(nums, target, middle + 1, height)
        }
    }

    fun searchInsertWithoutRecursion(nums: IntArray, target: Int): Int {
        var middle: Int
        var low = 0
        var heigh = nums.size - 1

        while (low <= heigh) {
            middle = low + (heigh - low) / 2

            if (nums[middle] == target) {
                return middle
            } else if (target < nums[middle]) {
                heigh = middle - 1
            } else {
                low = middle + 1
            }
        }

        return low
    }

    fun searchInsertSuperEfficient(nums: IntArray, target: Int): Int {
        var middle: Int
        var low = 0
        var heigh = nums.size

        while (low < heigh) {
            middle = (low + heigh) / 2

            if (target > nums[middle]) {
                low = middle + 1
            } else {
                heigh = middle
            }
        }

        return heigh
    }
}

class Solution977 {

    fun sortedSquares(nums: IntArray): IntArray = nums.map { it * it }.sorted().toIntArray()
}

class Solution189 {

    fun rotate(nums: IntArray, k: Int) {
        if (nums.size == k) return

        val newArray = IntArray(nums.size)
        nums.forEachIndexed { i, item -> newArray[i] = item }

        newArray.forEachIndexed { i, item ->
            val moveFactor = if (i + k > nums.size - 1) {
                (i + k) % (nums.size) // Divide by module where the reminder is additional position which we need to move
            } else {
                i + k
            }

            nums[moveFactor] = item
        }
    }
}

class Solution283 {

    fun moveZeroes(nums: IntArray): Unit {
        var counter = 0
        nums.forEachIndexed { i, item ->
            if (nums[i] == 0) {
                counter++
            } else {
                if (counter > 0) {
                    val number = nums[i]
                    nums[i] = 0
                    nums[i - counter] = number
                }
            }
        }
        println(nums.joinToString { it.toString() })
    }
}

class Solution167 {

    fun twoSum(numbers: IntArray, target: Int): IntArray {
        var i = 0
        var j = numbers.size - 1

        while (numbers[i] + numbers[j] != target) {
            if (numbers[i] + numbers[j] < target) i++ else j--
        }

        return intArrayOf(i, j).apply { println(joinToString { it.toString() }) }
    }
}

class Solution344 {

    fun reverseString(s: CharArray): Unit {
        var i = 0
        var t: Char
        while (i != s.size/2) {
            t = s[i]
            s[i] = s[s.size - 1 - i]
            s[s.size - 1 - i] = t
            i++
        }

        println(s.joinToString { it.toString() })
    }
}

class Solution557 {

    fun reverseWords(s: String): String {
        val emptySpacePositions = IntArray(s.length)
        var j = 0
        s.forEachIndexed { i, item ->
            if (item == ' ') {
                emptySpacePositions[j] = i
                j++
            }
        }
        val sArray = s.toCharArray()
        val counter = j + 1
        j = 0


        var c = 0
        while (j != counter ) {
            val begin = when (j) {
                0 -> 0
                counter - 1 -> emptySpacePositions[j - 1] + 1
                else -> emptySpacePositions[j - 1] + 1
            }
            val end = if (j == counter - 1) s.length - 1 else emptySpacePositions[j] - 1

            val t = s[begin + c]
            sArray[begin + c] = s[end - c]
            sArray[end - c] = t
            c++

            if (c > (end - begin)/2) {
                j++
                c = 0
            }
        }


        val str = StringBuilder()
        sArray.forEach { str.append(it) }

        return str.toString()
    }
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}
class Solution876 {

    val node = ListNode(1).apply { next = ListNode(2).apply { next = ListNode(3).apply { next = ListNode(4).apply { next = ListNode(5).apply { next = ListNode(6) } } } } }
    val nodeTwo = ListNode(1).apply { next = ListNode(2) }

    fun middleNode(head: ListNode?): ListNode? {
        var counter = 0
        var current = head

        while (current?.next != null) {
            counter++
            current = current.next
        }

        val middle = if (counter % 2 == 0) {
            (counter / 2) + 1
        } else {
            Math.round(counter.toDouble() / 2.0).toInt() + 1
        }

        counter = 0
        current = head

        while (current?.next != null && counter < middle - 1) {
            counter++
            current = current.next
        }

        return current
    }
}

class Solution19 {

    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        var counter = if (head != null) 1 else 0
        var current = head

        while (current?.next != null) {
            counter++
            current = current.next
        }

        val length = counter - n
        if (head?.next == null) return null
        if (length == 0) return head.next

        counter = 0
        current = head

        while (current?.next != null) {
            if (counter == length - 1) {
                current.next = current.next?.next
                break
            }

            current = current.next
            counter++
        }

        return head
    }
}

class Solution3 {

    fun lengthOfLongestSubstring(s: String): Int {
        var start = 0
        var counter = 0
        val map = HashMap<Char, Int>()

        s.forEachIndexed { i, it ->
            if (map.containsKey(it)) {
                if (map.get(it) != null && map.get(it)!! >= start) {
                    start = map.get(it)!! + 1
                }
            }
            counter = Math.max(counter, i - start + 1)
            map.put(it, i)
        }

        return counter
    }
}

class Solution567 {

    fun checkInclusion(s1: String, s2: String): Boolean {
        val short = if (s1.length <= s2.length) s1 else s2
        val long = if (s1.length > s2.length) s1 else s2

        if (s2.length < s1.length) return false

        val letters = IntArray(28) // english letters
        short.forEachIndexed { _, item ->
            letters[item.code - 'a'.code]++
        }

        var left = 0
        while (left + short.length <= long.length) {
            val newLetters = letters.clone()
            var flag = true

            for (i in left until left + short.length) {
                newLetters[long[i].code - 'a'.code]--
                if (newLetters[long[i].code - 'a'.code] < 0) flag = false
            }

            if (flag) return true
            left++
        }

        return false
    }
}

class Solution733 {

    /**
     * 1 1 1
     * 1 1 0
     * 1 0 1
     */
    fun floodFill(image: Array<IntArray>, sr: Int, sc: Int, color: Int): Array<IntArray> {
        return floodFill(image, sr, sc, image[sr][sc], color)
    }

    private fun floodFill(image: Array<IntArray>, sr: Int, sc: Int, oldColor: Int, newColor: Int): Array<IntArray> {
        if (sr < 0 || sc < 0) return image
        if (sr >= image.size || sc >= image.first().size ) return image
        if (oldColor == newColor) return image
        if (image[sr][sc] != oldColor) return image

        image[sr][sc] = newColor

        floodFill(image, sr - 1, sc, oldColor, newColor) //left
        floodFill(image, sr + 1, sc, oldColor, newColor) // right
        floodFill(image, sr, sc - 1, oldColor, newColor) // up
        floodFill(image, sr, sc + 1, oldColor, newColor) //down

        return image
    }
}

class Solution695 {

//    arrayOf(
//    intArrayOf(0,0,1,0,0,0,0,1,0,0,0,0,0),
//    intArrayOf(0,0,0,0,0,0,0,1,1,1,0,0,0),
//    intArrayOf(0,1,1,0,1,0,0,0,0,0,0,0,0),
//    intArrayOf(0,1,0,0,1,1,0,0,1,0,1,0,0),
//    intArrayOf(0,1,0,0,1,1,0,0,1,1,1,0,0),
//    intArrayOf(0,0,0,0,0,0,0,0,0,0,1,0,0),
//    intArrayOf(0,0,0,0,0,0,0,1,1,1,0,0,0),
//    intArrayOf(0,0,0,0,0,0,0,1,1,0,0,0,0),
//    )

    var max = 0
    var c = 0
    fun maxAreaOfIsland(grid: Array<IntArray>): Int {
        for (i in grid.indices) {
            for (j in grid.first().indices) {
                if (grid[i][j] == 1) {
                    count(grid, i, j, 1)
                    c = 0
                }
            }
        }
        return max
    }

    private fun count(grid: Array<IntArray>, i: Int, j: Int, counter: Int) {
        if (i < 0 || j < 0) return
        if (i >= grid.size || j >= grid.first().size ) return
        if (grid[i][j] == 5 || grid[i][j] != 1) return

        grid[i][j] = 5
        c++
        max = if (c > max) c else max

        if (i-1 >= 0 && grid[i-1][j] == 1) count(grid, i-1, j, counter + 1) // up
        if (i+1 < grid.size && grid[i+1][j] == 1) count(grid, i+1, j, counter + 1) // down

        if (j-1 >=0 && grid[i][j-1] == 1) count(grid, i, j-1, counter + 1) // left
        if (j+1 < grid.first().size && grid[i][j+1] == 1) count(grid, i, j+1, counter + 1) // right
    }
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}
class Solution617 {
//    TreeNode(1).apply {
//        left = TreeNode(3).apply { left = TreeNode(5) }
//        right = TreeNode(2)
//    },
//    TreeNode(2).apply {
//        left = TreeNode(1).apply { right = TreeNode(4) }
//        right = TreeNode(3).apply { right = TreeNode(7) }
//    }

    fun mergeTrees(root1: TreeNode?, root2: TreeNode?): TreeNode? {
        if (root1 == null && root2 == null) return null
        if (root1 == null) return root2
        if (root2 == null) return root1

        val merged = TreeNode(root1.`val` + root2.`val`)
        merge(root1, root2, merged)

        return merged
    }

    private fun merge(root1: TreeNode?, root2: TreeNode?, merged: TreeNode?) {
        if (root1 == null && root2 == null) return

        if (root1?.left != null || root2?.left != null) {
            merged?.left = TreeNode((root1?.left?.`val` ?: 0) + (root2?.left?.`val` ?: 0))
        }

        if (root1?.right != null || root2?.right != null) {
            merged?.right = TreeNode((root1?.right?.`val` ?: 0) + (root2?.right?.`val` ?: 0))
        }

        merge(root1?.left, root2?.left, merged?.left)
        merge(root1?.right, root2?.right, merged?.right)
    }
}

class Node(var `val`: Int) {
    var left: Node? = null
    var right: Node? = null
    var next: Node? = null
}
class Solution116 {

    fun connect(root: Node?): Node? {

        if (root == null) return null

        traverse(root, 0, mutableListOf())

        return root
    }

    private var maxDepth = 0
    private fun traverse(root: Node?, depth: Int, list: MutableList<Node>) {
        if (root == null) return

        traverse(root.right, depth + 1, list)
        traverse(root.left, depth + 1, list)

        maxDepth = if(depth > maxDepth) depth else maxDepth

        if (list.size <= maxDepth - depth) {
            list.add(root)
        } else {
            root.next = list[maxDepth - depth]
            list[maxDepth - depth] = root
        }
    }
}

class Solution542 {

    //    arrayOf(
//    intArrayOf(0,0,0),
//    intArrayOf(0,1,0),
//    intArrayOf(1,1,1)
//    )
    fun updateMatrix(mat: Array<IntArray>): Array<IntArray> {
        val m: Int = mat.size
        val n: Int = mat[0].size
        val maxDistance = m + n

        for (i in 0 until m) {
            for (j in 0 until n) {
                if (mat[i][j] != 0) {
                    var top = maxDistance
                    var left = maxDistance
                    if (i - 1 >= 0) top = mat[i - 1][j]
                    if (j - 1 >= 0) left = mat[i][j - 1]
                    mat[i][j] = Math.min(top, left) + 1
                }
            }
        }
        for (i in m - 1 downTo 0) {
            for (j in n - 1 downTo 0) {
                if (mat[i][j] != 0) {
                    var bottom = maxDistance
                    var right = maxDistance
                    if (i + 1 < m) bottom = mat[i + 1][j]
                    if (j + 1 < n) right = mat[i][j + 1]
                    mat[i][j] = Math.min(mat[i][j], Math.min(bottom, right) + 1)
                }
            }
        }
        return mat
    }
}

class Solution994 {

    data class Position(val i: Int, val j: Int)

    fun orangesRotting(grid: Array<IntArray>): Int {
        val queue = ArrayDeque<Position>()
        var counter = 0

        if (grid.size == 1 && grid.first().size == 1 && grid[0][0] == 0) return 0
        var freshCells = 0

        for(i in grid.indices) {
            for(j in grid.first().indices) {
                if (grid[i][j] == 2) { //rotten cell
                    queue.add(Position(i, j))
                } else if (grid[i][j] == 1) {
                    freshCells++
                }
            }
        }

        while(queue.isNotEmpty()) {
            val items = mutableListOf<Position>().apply {
                queue.forEach { _ ->
                    add(queue.pop())
                }
            }
            val size = queue.size

            items.forEach { item ->
                if (item.j + 1 <= grid.first().size-1 && grid[item.i][item.j+1] == 1) {
                    queue.add(Position(item.i, item.j+1))
                    grid[item.i][item.j+1] = 2
                    freshCells--
                } // right
                if (item.i + 1 <= grid.size-1 && grid[item.i+1][item.j] == 1) {
                    queue.add(Position(item.i+1, item.j))
                    grid[item.i+1][item.j] = 2
                    freshCells--
                } // bottom
                if (item.j - 1 >= 0 && grid[item.i][item.j-1] == 1) {
                    queue.add(Position(item.i, item.j-1))
                    grid[item.i][item.j-1] = 2
                    freshCells--
                } // left
                if (item.i - 1>= 0 && grid[item.i-1][item.j] == 1) {
                    queue.add(Position(item.i-1, item.j))
                    grid[item.i-1][item.j] = 2
                    freshCells--
                } // top
            }

            if (queue.size > size) counter++
        }

        return if (freshCells <=0 ) counter else -1
//        for(i in grid.indices) {
//            for(j in grid.first().indices) {
//                if (grid[i][j] == 1) {
//                    return -1
//                }
//            }
//        }

    }
}

class Solution21 {

    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        if (list1 == null && list2 == null) return null
        if (list1 == null) return list2
        if (list2 == null) return list1

        var newNode: ListNode? = null

        var c1 = list1
        var c2 = list2

        var tail: ListNode?
        var _tail: ListNode?

        if (list1.`val` == list2.`val` || list1.`val` < list2.`val`) {
            tail =  ListNode(list1.`val`)
            newNode = tail
            c1 = c1.next
        } else {
            tail =  ListNode(list2.`val`)
            newNode = tail
            c2 = c2.next
        }

        while (c1 != null || c2 != null) {
            if (c1 == null && c2 != null) {
                _tail = ListNode(c2.`val`)
                tail?.next = _tail
                tail = _tail
                c2 = c2.next
            } else if (c2 == null && c1 != null) {
                _tail = ListNode(c1.`val`)
                tail?.next = _tail
                tail = _tail
                c1 = c1.next
            } else {
                if (c1!!.`val` == c2!!.`val` || c1.`val` < c2.`val`) {
                    _tail = ListNode(c1.`val`)
                    tail?.next = _tail
                    tail = _tail
                    c1 = c1.next
                } else {
                    _tail = ListNode(c2.`val`)
                    tail?.next = _tail
                    tail = _tail
                    c2 = c2.next
                }
            }
        }

        return newNode
    }
}

class Solution206 {

    fun reverseList(head: ListNode?): ListNode? {
        if (head == null) return null

        var current = ListNode(head.`val`)
        var nextToCurrent: ListNode? = head.next?.let { ListNode(it.`val`) } ?: return current
        var feature = head.next?.next

        nextToCurrent?.next = current
        if (feature == null) return nextToCurrent

        while (feature != null) {
            current = ListNode(feature.`val`)
            current.next = nextToCurrent

            nextToCurrent = feature.next?.let { ListNode(it.`val`) }
            if (nextToCurrent == null) {
                nextToCurrent = current
            } else {
                nextToCurrent.next = current
            }

            feature = feature.next?.next
        }

        return nextToCurrent
    }
}

class Solution77 {

    private var n = 0
    private var k = 0
    private val output: MutableList<List<Int>> = LinkedList()

    fun combine(n: Int, k: Int): List<List<Int>> {
        this.n = n
        this.k = k

        combine(1, LinkedList<Int>())

        return output
    }

    private fun combine(first: Int, current: LinkedList<Int>) {
        if (current.size == k) {
            output.add(LinkedList(current))
        }

        for (i in first until n + 1) {
            current.add(i)

            combine(i  + 1, current)

            current.removeLast()
        }
    }
}

class Solution46 {


    fun permute(nums: IntArray): List<List<Int>> {
        return permute(mutableListOf(), nums.toMutableList(), mutableListOf())
    }

    private fun permute(set: MutableList<List<Int>>, current: MutableList<Int>, leftover: MutableList<Int>): MutableList<List<Int>> {
        if (current.isEmpty()) {
            set.add(leftover)
            return set
        }

        for(i in current.indices) {
            val item = current[i]
            val _leftover = mutableListOf<Int>()
            for (j in current.indices) {
                if (i != j) _leftover.add(current[j])
            }

            val newLeftover = mutableListOf<Int>().apply {
                addAll(leftover)
                add(item)
            }

            permute(set, _leftover, newLeftover)
        }

        return set
    }
}

class Solution784 {

    private val map = mutableMapOf<String, Int>()

    fun letterCasePermutation(s: String): List<String> {
        compute(StringBuilder(s))

        return map.keys.toList()
    }

    private fun compute(s: StringBuilder) {
        s.forEachIndexed { i, it ->
            map[s.toString()] = i
            if (it.isLowerCase()) {
                val item = StringBuilder(s)
                item[i] = it.toUpperCase()
                if (map.contains(item.toString()).not()) {
                    compute(item)
                }
            }
            else {
                val item = StringBuilder(s)
                item[i] = it.toLowerCase()
                if (map.contains(item.toString()).not()) {
                    compute(item)
                }
            }
        }
    }
}

class Solution70 {

    fun climbStairs(n: Int): Int {
        val array = IntArray(n  + 1)
        Arrays.fill(array, -1)

        return climb(n, array)
    }

    private fun climb(n: Int, array: IntArray): Int {
        return if (n < 0 ) {
            0
        } else if (n == 0) {
            1
        } else if (array[n] > -1) {
            array[n]
        }
        else {
            array[n] = climb(n-1, array) + climb(n-2, array)
            array[n]
        }
    }
}

class Solution198 {

    private var gSum = -1
    fun robTooSlow(nums: IntArray): Int {
        if (nums.size == 1) return nums.first()
        robTooSlow(nums, 0, 2, 0)

        return gSum
    }

    private fun robTooSlow(nums: IntArray, position: Int, step: Int, sum: Int) {
        if (position + step < nums.size) {
            robTooSlow(nums, position + step, step, sum + nums[position])
        } else {
            val newSum = sum + nums[position]
            if (newSum > gSum) gSum = newSum
        }
        if (position + 1 < nums.size) {
            robTooSlow(nums, position + 1, step, sum)
        }
    }

    private val memo = IntArray(100)
    fun rob(nums: IntArray): Int {
        memo.fill(-1)

        return rob(0, nums)
    }

    private fun rob(position: Int, nums: IntArray): Int {
        return if (position >= nums.size) {
            0
        }
        else if (memo[position] != -1) {
            memo[position]
        }
        else {
            val sum = Math.max(rob(position+1, nums), rob(position+2, nums) + nums[position])
            memo[position] = sum

            sum
        }
    }
}

class Solution120 {

    private val set = mutableMapOf<String, Int>()
    fun minimumTotalSlow(triangle: List<List<Int>>): Int {
        return min(triangle, 0, 0)
    }

    private fun min(triangle: List<List<Int>>, r: Int, i: Int): Int {
        if (set.containsKey("$r$i")) return set["$r$i"]!!

        var item = triangle[r][i]

        if (r < triangle.size - 1) {
            val left = min(triangle, r+1, i)
            val right = min(triangle, r+1, i+1)
            item += left.coerceAtMost(right)
        }

        set["$r$i"] = item

        return item
    }
}

class Solution231 {

    fun isPowerOfTwo(n: Int): Boolean {
        var i = 1L
        while (i < n) {
            i *= 2
        }

        return i == n.toLong()
    }

    fun isPowerOfTwoBook1(n: Int): Boolean {
        if (n == 0) return false
        val x = n.toLong()
        return (x and (-x)) == x
    }

    fun isPowerOfTwoBook2(n: Int): Boolean {
        if (n == 0) return false
        val x = n.toLong()
        return (x and (x-1)) == 0L
    }
}

class Solution191 {

    fun hammingWeightFirst(n: Int): Int {
        var counter = 0

        Integer.toBinaryString(n).forEach {
            if (it == '1') counter++
        }

        return counter
    }

    fun hammingWeight(n: Int):Int {
        var counter = 0

        if (n < 0) {
            negativeTo2Complement(n.toLong()).forEach {
                if (it == '1') counter++
            }
        } else {
            n.toString(2).forEach {
                if (it == '1') counter++
            }
        }

        return counter
    }

    /**
     * We take the positive of that negative and flip all bits to opposite and plus it with 1
     * Ex -2
     *  -- 2D = 10B
     *  -- flip = 01B
     *  -- add 1 = 10B
     */
    private fun negativeTo2Complement(n: Long): String {
        var uN = Math.abs(n).toString(2)
        while (uN.length < Int.SIZE_BITS) { uN = "0$uN"
        }
        val s = StringBuilder()
        uN.forEach {
            if (it == '1') {
                s.append('0')
            } else {
                s.append('1')
            }
        }
        val sB = s.toString().toLong(2) or 1
        return sB.toString(2)
    }

    fun hammingWeightBook(n: Int): Int {
        var _n= n
        var counter = 0
        while (_n != 0) {
            counter++
            _n = _n and (_n - 1)
        }

        return counter
    }
}

class Solution190 {

    fun reverseBits(n:Int):Int {
        return Integer.reverse(n)
    }
}

class Solution136 {

    fun singleNumber(nums: IntArray): Int {
        if (nums.size == 1) return nums.first()
        val bitSet = BitSet(30_000)

        nums.forEach {
            if (bitSet.get(it + 30_000)) bitSet.clear(it + 30_000) else bitSet.set(it + 30_000)
        }

        nums.forEach {
            if(bitSet.get(it + 30_000)) return it
        }

        return 0
    }
}