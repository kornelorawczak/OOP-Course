class Length 
    def initialize(meters)
        @km = meters.to_f / 1000
        @m = meters
        @nm = meters.to_f / 1852 # nautical mile
        @mm = meters * 1000
    end 
    attr_accessor :km, :nm, :mm
end 

class Time 
    def initialize(minutes)
        @h = minutes.to_f / 60
        @m = minutes 
        @s = minutes * 60
    end 
    attr_accessor :h, :s
end 

class Velocity 
    def initialize(meters, minutes)
        len = Length.new(meters)
        t = Time.new(minutes)
        @kmh = len.km / t.h
        @knots = len.nm / t.h
    end 
    def kmhs()
        @kmh
    end 
    def knts()
        @knots
    end 
end 

class Acceleration 
    def initialize(meters, minutes)
        len = Length.new(meters)
        t = Time.new(minutes)
        @kms2 = len.km / (t.s * t.s) 
        @mmh2 = len.mm / (t.h * t.h)
    end 
    
    def kms 
        @kms2
    end 
    def mmh
        @mmh2
    end 
end 

v1 = Velocity.new(3000, 3)
v2 = Velocity.new(54000, 5)
a1 = Acceleration.new(100, 0.02)
a2 = Acceleration.new(0.2, 0.0001)

puts "Velocity:"
puts "SI          | non SI"
puts "------------|------------"
puts "#{"%.2f" %v1.kmhs} km/h  | #{"%.2f" %v1.knts} knots"
puts "#{"%.2f" %v2.kmhs} km/h | #{"%.2f" %v2.knts} knots"

# Wydruk tabelki dla przyspieszenia
puts "\nAcceleration:"
puts "SI          | non SI"
puts "------------|------------"
puts "#{"%.2f" %a1.kms} km/s2  | #{"%.2f" %a1.mmh} mm/h2"
puts "#{"%.2f" %a2.kms} km/s2  | #{"%.2f" %a2.mmh} mm/h2"