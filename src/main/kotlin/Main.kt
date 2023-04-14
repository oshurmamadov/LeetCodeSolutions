fun main(args: Array<String>) {
    println(Solution438().findAnagrams(
        "cbaebabacd", "abc"
    ).apply {
        println()
    })
}

class Solution34 {

    fun searchRange(nums: IntArray, target: Int): IntArray {
        if (nums.isEmpty()) return intArrayOf(-1, -1)
        val range = mutableListOf<Int>()
        val first = search(nums, target, toLeft = true)
        if (first == -1) return intArrayOf(-1, -1)
        val last = search(nums, target, toLeft = false)

        range.add(first)
        range.add(last)

        return range.toIntArray()
    }

    private fun search(nums: IntArray, target: Int, toLeft: Boolean): Int {
        var begin = 0
        var end = nums.size

        while (begin < end) {
            val middle = begin + (end - begin) / 2

            if (nums[middle] == target) {
                if (toLeft) {
                    if (middle != 0 && nums[middle-1] == target) {
                        end = middle
                    } else {
                        return middle
                    }
                } else {
                    if (middle != nums.size-1 && nums[middle+1] == target) {
                        begin = middle
                    } else {
                        return middle
                    }
                }
            } else if (target > nums[middle]) {
                begin = middle + 1
            } else {
                end = middle
            }
        }

        return -1
    }
}

class Solution33 {

    fun search(nums: IntArray, target: Int): Int {
        return search(nums, target, 0, nums.size)
    }

    private fun search(nums: IntArray, target: Int, begin: Int, end: Int): Int {
        if (begin >= end) return -1

        val middle = begin + (end - begin) / 2

        if (nums[middle] == target) {
            return middle
        }
        val left = search(nums, target, begin, middle)
        val right = search(nums, target, middle+1, end)

        if (left != -1) return left
        if (right != -1) return right
        return -1
    }
}

class Solution74 {

    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        matrix.forEachIndexed { i, item ->
            if (item.last() > target) {
                return search(item, target) != -1
            }
        }

        return false
    }

    private fun search(nums: IntArray, target: Int): Int {
        var begin = 0
        var end = nums.size

        while (begin < end) {
            val middle = (begin + end) / 2

            if (nums[middle] == target) {
                return middle
            } else if (target > nums[middle]) {
                begin = middle + 1
            } else {
                end = middle
            }
        }

        return -1
    }
}

class Solution153 {

    var min = Int.MAX_VALUE

    fun findMin(nums: IntArray): Int {
        find(nums, 0, nums.size)

        return min
    }

    private fun find(nums: IntArray, begin: Int, end: Int) {
        if (begin >= end) return
        val middle = begin + (end - begin) / 2

        if ((end-begin) == 1) {
            if (nums[middle] < min) min = nums[middle]
        } else if (nums[middle] < nums[middle - 1]) {
            min = nums[middle]
            return
        } else {
            find(nums, begin, middle)
            find(nums, middle + 1, end)
        }
    }
}

class Solution162 {

    private var peak = 0

    fun findPeakElement(nums: IntArray): Int {
        find(nums, 0, nums.size - 1)

        return peak
    }

    private fun find(nums: IntArray, begin: Int, end: Int) {
        if (begin == end) {
            peak = begin
            return
        }
        val middle = begin + (end - begin) / 2

        if (nums[middle] > nums[middle + 1]) {
            find(nums, begin, middle)
        } else {
            find(nums, middle + 1, end)
        }
    }
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class Solution82 {

    fun run() {
        val node = ListNode(1).apply { next = ListNode(2).apply { next = ListNode(2).apply { next = ListNode(3).apply { next = ListNode(3).apply {  next = ListNode(4) } } } } }

        val res = deleteDuplicates(node)

        println(res)
    }

    fun deleteDuplicates(head: ListNode?): ListNode? {

        var current: ListNode? = head

        var finalValue: ListNode? = null

        var unique: ListNode? = null
        var uniqueValue: Int? = null

        while (current != null) {
            if (uniqueValue != current.`val`) {
                uniqueValue = current.`val`

                if (unique == null && current.`val` != current.next?.`val`) {
                    unique = ListNode(current.`val`)
                    finalValue = unique
                } else if (current.`val` != current.next?.`val`) {
                    unique?.next = ListNode(current.`val`)
                    unique = unique?.next
                }
            }

            current = current.next
        }

        return finalValue
    }
}

class Solution15 {


    // Platform solution. Was too dumb to figure it out.
    fun threeSum(nums: IntArray): List<List<Int>> {
        val res = mutableSetOf<List<Int>>()
        val dups = hashSetOf<Int>()
        val seen = hashMapOf<Int,Int>()

        nums.forEachIndexed { i, item ->
            if (dups.add(nums[i])) {
                for (j in i+1 until nums.size) {
                    val compliment = -nums[i] - nums[j]
                    if (seen.containsKey(compliment) && seen.get(compliment) == i) {
                        val triplet = arrayListOf(nums[i], nums[j], compliment).apply { sort() }
                        res.add(triplet)
                    }
                    seen.put(nums[j], i)
                }
            }
        }

        return ArrayList(res)
    }
}

class Solution844 {

    fun backspaceCompare(s: String, t: String): Boolean {
        val big = if (s.length > t.length) s else t
        val short = if (s.length <= t.length) s else t

        val bigStr = StringBuilder() //Why not to use stack ?
        val shortStr = StringBuilder()

        big.forEachIndexed { index, item ->
            if (item == '#') {
                if (bigStr.isNotEmpty()) {
                    bigStr.deleteCharAt(bigStr.length - 1) // stack push
                }
            } else {
                bigStr.append(item) // stack pop
            }

            if (index < short.length) {
                if (short[index] == '#') {
                    if (shortStr.isNotEmpty()) {
                        shortStr.deleteCharAt(shortStr.length - 1)
                    }
                } else {
                    shortStr.append(short[index])
                }
            }

        }

        return bigStr.toString() == shortStr.toString()
    }
}

class Solution986 {

    fun intervalIntersection(firstList: Array<IntArray>, secondList: Array<IntArray>): Array<IntArray> {
        if (firstList.isEmpty() or secondList.isEmpty()) return emptyArray()

        val array = arrayListOf<IntArray>()

        for (i in firstList.indices) {
            val i1 = firstList[i].first()
            val i2 = firstList[i].last()

            //if (i < secondList.size) {
                //if (i1 <= secondList[i].last() && i2 >= secondList[i].first()) {
                    for (j in secondList.indices) {
                        val j1 = secondList[j].first()
                        val j2 = secondList[j].last()

                        if (i1 <= j2 && i2 >= j1) {
                            val _i = if (j1 >= i1) {
                                j1
                            } else if (j1 < i2) {
                                i1
                            } else {
                                -1
                            }

                            val _j = if (i2 <= j2) {
                                i2
                            } else if (i2 > j1) {
                                j2
                            } else {
                                -1
                            }

                            val _array = intArrayOf(_i, _j)
                            array.add(_array)
                        } 
                    }
              //  }
           // }
        }

        return array.toTypedArray()
    }
}

class Solution11 {

    fun maxArea(height: IntArray): Int {
        var max = 0

        var i = 0
        var j = height.size - 1

        while (i < j) {
            if (height[i] <= height[j]) {
                max = Math.max(max, height[i] * (j - i))
                i++
            } else {
                max = Math.max(max, height[j] * (j - i))
                j--
            }
        }

        return max
    }
}

class Solution438 {

    fun findAnagrams(s: String, p: String): List<Int> {
        val map = getDictionary(p)
        val newMap = HashMap<Char, Int>()
        val list = mutableListOf<Int>()

        for (i in s.indices) {

            if (newMap.containsKey(s[i])) {
                val value = newMap[s[i]] ?: 0
                newMap[s[i]] = value + 1
            } else {
                newMap[s[i]] = 1
            }

            if (i > p.length - 1) {
                val leftMost = s[i - p.length]
                if (newMap.containsKey(leftMost)) {
                    val value = newMap[leftMost] ?: 0
                    if (value == 0) {
                        newMap.remove(leftMost)
                    } else {
                        newMap[leftMost] = value - 1
                        if (newMap[leftMost] == 0) {
                            newMap.remove(leftMost)
                        }
                    }
                }
            }

            if (map == newMap) {
                list.add(i - (p.length - 1))
            }
        }

        return list
    }

    private fun getDictionary(a: String): HashMap<Char, Int> {
        val map = HashMap<Char, Int>()
        a.forEach {
            if (map.containsKey(it)) {
                val value = map[it] ?: 0
                map[it] = value + 1
            } else {
                map[it] = 1
            }
        }
        return map
    }

}
