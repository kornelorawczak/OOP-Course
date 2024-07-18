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
    def initialize()
        @head = @tail = nil
        @size = 0
    end 
    def push(value)
        new_node = Node.new(value)
        if @head == nil 
            @head = @tail = Node.new(value)
            @size = 1
            return
        elsif value <= hdval
            @head.prev = new_node 
            new_node.next = @head 
            @head = new_node
            @size += 1
        elsif value >= tlval
            @tail.next = new_node 
            new_node.prev = @tail 
            @tail = new_node 
            @size += 1
        else 
            starter = @head
            while starter.next != nil 
                if starter.value <= value and value <= starter.next.value 
                    new_node.next = starter.next
                    new_node.prev = starter 
                    starter.next.prev = new_node
                    starter.next = new_node 
                    @size += 1
                    return 
                end 
                starter = starter.next
            end 
        end 
    end 
            
    def length()
        @size
    end 
    def get(i)
        if i >= @size
            puts "Out of index"
            return 
        elsif i <= @size / 2 
            j = 0
            starter = @head 
            while j != i 
                starter = starter.next
                j += 1
            end 
            starter.value
        else 
            j = @size - 1
            starter = @tail 
            while j != i 
                starter = starter.prev
                j -= 1
            end 
            starter.value 
        end 
    end 
    def hdval()
        if @head != nil 
            @head.value 
        else nil 
        end
    end  
    def tlval()
        if @tail != nil 
            @tail.value 
        else nil 
        end
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
    def reverse_print()
        if @head == nil 
            puts "empty list"
            return 
        end 
        starter = @tail
        print "[" 
        while starter.prev != nil 
            print "#{starter.value}, " 
            starter = starter.prev
        end 
        puts "#{starter.value}]" 
    end 
    
end 

class Search 
    def self.binary_search(collection, value)
        if collection.hdval > value or collection.tlval < value 
            return nil 
        end 
        left = 0 
        right = collection.length - 1
        while left <= right 
            mid = (left + right) / 2
            mid_val = collection.get(mid)
            if mid_val == value 
                return value 
            elsif mid_val < value 
                left = mid + 1
            else 
                right = mid - 1
            end 
        end 
        return nil 
    end 
    def self.interpolation_search(collection, value)
        if collection.hdval > value or collection.tlval < value 
            return nil 
        end 
        left = 0 
        right = collection.length - 1
        while left <= right and value >= collection.get(left) and value <= collection.get(right)
            pres = left + ((value - collection.get(left)) * (right - left) / (collection.get(right) - collection.get(left)))
            pres_val = collection.get(pres)
            if pres_val == value
                return value 
            elsif pres_val < value 
                left = pres + 1
            else 
                right = pres - 1
            end 
        end 
        return nil 
    end  
end


test_list = Collection.new
test_list.push(2)
test_list.push(4)
test_list.push(0)
test_list.push(-5)
test_list.push(1)
test_list.push(5)
test_list.print_list()
test_list.reverse_print()
puts Search.binary_search(test_list, 4)
puts Search.interpolation_search(test_list, 2)