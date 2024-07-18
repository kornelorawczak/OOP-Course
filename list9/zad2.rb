class Function2D
    def initialize(&block)
        @function = block
    end
    def value(x, y)
        @function.call(x, y)
    end 
    def volume(a, b, c, d)
        volume = 0.0
        x = a
        step = 0.1 # manually set step that sets the precision level
        while x <= b 
            y = c
            while y <= d 
                volume += value(x, y) * step * step # we use a rectangle method to calculate the volume
                y += step
            end  
            x += step
        end 
        volume 
    end 
    def contour_line(a, b, c, d, height)
        step = 0.1
        epsilon = 0.001 
        pairs = []
        x = a
        while x <= b 
            y = c
            while y <= d 
                if (value(x, y) - height).abs <= epsilon 
                    pairs << [x, y]
                end 
                y += step 
            end 
            x += step 
        end 
        pairs 
    end 
end 

my_function = Function2D.new { |x, y| x + y } 
puts my_function.value(10, 20)
puts my_function.volume(0, 10, 2, 8)
my_pairs = my_function.contour_line(0, 4, 0, 10, 2)
my_pairs.each do |pair|
    puts "(#{pair[0]}, #{pair[1]})"
end 