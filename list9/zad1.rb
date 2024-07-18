

class Function 
    def initialize(&block)
        @function = block
    end 
    def value(x)
        @function.call(x)
    end 
    def zero(a, b, e)
        fa = value(a)
        fb = value(b)
        return nil if fa * fb > 0  
        while (b - a).abs > e 
            c = (a + b) / 2.0
            fc = value(c)
            return c if fc.abs < e
            if fa * fc < 0 
                b = c 
                fb = fc 
            else 
                a = c
                fa = fc 
            end 
        end 
        nil 
    end 
    def field(a, b)
        # we assume that we divide sector [a, b] into 1000 pieces, since that wasn't specified in the task
        range = ((b - a).abs) / 1000.0
        fa = value(a)
        field = 0.0
        0.upto(999) do 
            aa = a + range 
            faa = value(aa)
            field += range * ((fa + faa) / 2.0)
            a = aa 
            fa = faa 
        end
        field 
    end
    def deriv(x)
        (value(x + 0.0001) - value(x)) / 0.0001
    end 
    
end 


my_function = Function.new { |x| Math.sin(x) }
puts my_function.value(2)
puts my_function.zero(1, 4, 0.0001)
puts my_function.field(0, 3.14)
puts my_function.deriv(2)
