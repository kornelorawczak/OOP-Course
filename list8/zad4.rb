class EXPLICIT
    def initialize(text)
        @text = text 
    end

    def encode(key)
        encoded = ""
        @text.chars.each { |letter| encoded += key[letter] || letter }
        ENCRYPTED.new(encoded)
    end

    def to_s()
        @text
    end 
end

class ENCRYPTED
    def initialize(text)
        @text = text
    end 

    def decipher(key)
        deciphered = ""
        @text.chars.each{ |letter| deciphered += key.key(letter) || letter }
        EXPLICIT.new(deciphered)
    end 

    def to_s()
        @text
    end 
end 

dict = {
    'a' => 'c',
    'b' => 'e',
    'o' => 'y',
    'y' => 'u',
    'u' => 'a',
    'e' => 'b',
    'c' => 'o'
}

text = EXPLICIT.new("autonomic eb12")
ciphered = text.encode(dict)
puts ciphered.to_s
deciphered = ciphered.decipher(dict)
puts deciphered.to_s

