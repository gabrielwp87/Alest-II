
class Monkeys:
    def __init__(self, content, rules_even, rules_odd, content_for_stones, i):
        self.odd_stones = 0
        self.even_stones = 0
        self.content = content
        self.name = f'Macaco {i - 1}'
        self.rules_even = rules_even
        self.rules_odd = rules_odd
        self.content_for_stones = content_for_stones

    def get_rules_even(self):
        return self.rules_even

    def get_rules_odd(self):
        return self.rules_odd

    def stones(self):
        stones = self.content_for_stones
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


def get_monkeys_number(file):
    index_begin = file.index("_") + 1
    index_end = file.index("M")
    monkeys_number = int(file[index_begin:index_end].strip())
    return monkeys_number


def get_rounds(file):
    index_begin_rounds = file.index("_", 5) + 1
    index_end_rounds = file.index("R")
    number_of_rounds = int(
        file[index_begin_rounds:index_end_rounds].strip())
    return number_of_rounds
