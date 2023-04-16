

class Monkeys:
    def __init__(self, file, line):
        self.odd_stones = 0
        self.even_stones = 0
        self.file = file
        self.content = ""
        self.line = line
        self.name = f'Macaco {line -1}'
        self.rules_even = 0
        self.rules_odd = 0

    def set_rules(self):
        rules = self.get_content_for_rules()
        self.rules_even = int(rules[4]) - 1
        self.rules_odd = int(rules[7]) - 1  # -1 pois será usado para o index

    def get_rules_even(self):
        return self.rules_even

    def get_rules_odd(self):
        return self.rules_odd

    def read_file(self):
        txt = open(self.file)
        self.content = txt.readlines()
        return self.content

    def get_index_for_stones_content(self):
        index1 = self.content[self.line].find(":")
        index2 = self.content[self.line].find(":", index1 + 1)
        return index2 + 1

    def get_content_for_rules(self):
        index = self.get_index_for_stones_content()
        return self.content[self.line][:index].split()

    def get_content_for_stones(self):
        index = self.get_index_for_stones_content()
        return self.content[self.line][index:].split()

    def stones(self):
        stones = self.get_content_for_stones()
        for i in range(len(stones)):
            n = int(stones[i][-1])
            if n % 2 == 0:
                self.even_stones += 1
            else:
                self.odd_stones += 1

    def get_all_stones_number(self):
        return self.odd_stones + self.even_stones

    def get_even_stones_number(self):
        return self.even_stones

    def get_odd_stones_number(self):
        return self.odd_stones

    def give_even_stones(self):
        even_stones = self.even_stones
        self.even_stones = 0
        return even_stones

    def give_odd_stones(self):
        odd_stones = self.odd_stones
        self.odd_stones = 0
        return odd_stones

    def add_odd_stones(self, add_stones):
        self.odd_stones += add_stones

    def add_even_stones(self, even_stones):
        self.even_stones += even_stones

    def get_name(self):
        return self.name


class Game:
    def __init__(self, file):
        self.file = file
        self.monkeys_number = 0
        self.rounds = 0

    def get_monkeys_number(self):
        index_begin = self.file.index("_") + 1
        index_end = self.file.index("M")
        monkeys_number = int(self.file[index_begin:index_end].strip())
        return monkeys_number

    def get_rounds(self):
        index_begin_rounds = self.file.index("_", 5) + 1
        index_end_rounds = self.file.index("R")
        number_of_rounds = int(
            self.file[index_begin_rounds:index_end_rounds].strip())
        return number_of_rounds
