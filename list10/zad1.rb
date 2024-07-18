class Collection # double sided list 
    private
    class Node
        attr_accessor :value, :prev, :next
        def initialize(value)
            @value = value
            @prev = nil 
            @next = nil  
        end  
    end 
    public 
    def initialize(value)
        @head = @tail = Node.new(value) 
        @size = 1
    end 
    def pushback(value)
        new_val = Node.new(value)
        @tail.next = new_val
        new_val.prev = @tail
        @tail = new_val
        @size += 1
    end
    def push_front(value)
        new_val = Node.new(value)
        @head.prev = new_val
        new_val.next = @head
        @head = new_val
        @size += 1
    end 
    def remove(value)
        starter = @head 
        while starter != nil 
            if starter.value == value 
                if starter == @head 
                    @head = starter.next 
                    @head.prev = nil if @head != nil
                elsif starter == @tail 
                    @tail = starter.prev
                    @tail.next = nil 
                else 
                    starter.prev.next = starter.next 
                    starter.next.prev = starter.prev 
                end 
                @size -= 1
                return 
            end 
            starter = starter.next 
        end 
        puts "Value is not in the list!"
    end 
    def get(i)
        if i >= @size
            puts "Out of index"
            return 
        else 
            j = 0
            starter = @head 
            while j != i 
                starter = starter.next
                j += 1
            end 
            starter.value
        end 
    end 
    def swap(i, j)
        if i >= @size or j >= @size 
            puts "Out of index"
            return 
        elsif j < i 
            i, j = j, i # for simplification I'd like i to be lower than j
        else
            i_val = get(i)
            j_val = get(j)
            counter = 0
            current = @head 
            while counter != i 
                current = current.next
                counter += 1
            end 
            current.value = j_val 
            while counter != j
                current = current.next
                counter += 1
            end 
            current.value = i_val 
        end 
    end 

    def length()
        @size
    end 
    def print_list()
        if @head == nil 
            puts "empty list"
            return 
        end 
        starter = @head
        print "[" 
        while starter.next != nil 
            print "#{starter.value}, " 
            starter = starter.next
        end 
        puts "#{starter.value}]" 
    end 
end 

class Sorter 
    def self.sort1(list) # selection sort, usually more effective 
        n = list.length
        (0...n-1).each do |i|
            min_index = i
            ((i+1)...n).each do |j|
                if list.get(j) < list.get(min_index)
                    min_index = j 
                end 
            end
            list.swap(i, min_index) 
        end
    end
    def self.sort2(list) # bubble sort, usually less effective
        n = list.length
        (0...n).each do |i|
            (1...n-i).each do |j|
            if list.get(j - 1) > list.get(j)
                list.swap(j - 1, j) 
            end 
            end
        end
    end 
end 


test_list = Collection.new(5)
test_list.pushback(2)
test_list.push_front(10)
puts test_list.length
test_list.print_list
puts test_list.get(2)
test_list.swap(1, 2)
test_list.print_list
test_list.remove(2)
test_list.print_list

test_sort1 = Collection.new(12)
test_sort1.pushback(5)
test_sort1.pushback(2)
test_sort1.pushback(1)
test_sort1.pushback(-2)
test_sort1.pushback(9)
Sorter.sort1(test_sort1)
test_sort1.print_list

test_sort2 = Collection.new(12)
test_sort2.pushback(5)
test_sort2.pushback(2)
test_sort2.pushback(1)
test_sort2.pushback(-2)
test_sort2.pushback(9)
Sorter.sort2(test_sort2)
test_sort2.print_list