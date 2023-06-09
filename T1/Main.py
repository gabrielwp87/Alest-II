from T1 import Monkeys, get_monkeys_number, get_rounds
import time


file_50_macacos = "caso_50Macacos_5000Rodadas.txt"
file_100_macacos = "caso_100Macacos_10000Rodadas.txt"
file_200_macacos = "caso_200Macacos_20000Rodadas.txt"
file_400_macacos = "caso_400Macacos_40000Rodadas.txt"
file_600_macacos = "caso_600Macacos_60000Rodadas.txt"
file_800_macacos = "caso_800Macacos_80000Rodadas.txt"
file_900_macacos = "caso_900Macacos_90000Rodadas.txt"
file_1000_macacos = "caso_1000Macacos_100000Rodadas.txt"


# Main

def Games(file):
    start = time.time()
    participants = []
    winner_name = ""

    monkey_number = get_monkeys_number(file)
    number_rounds = get_rounds(file)

    txt = open(file)
    content = txt.readlines()
    # Macaco 0 par -> 43 impar -> 25 : 328 : 103304
    for i in range(1, monkey_number + 1):
        line = content[i].split()
        rules_even = int(line[4])
        rules_odd = int(line[7])
        content_for_stones = line[11:]
        monkey = Monkeys(rules_even,
                         rules_odd, content_for_stones, i)
        monkey.stones()
        participants.append(monkey)

    for i in range(number_rounds):
        for j in range(monkey_number):
            if participants[j].get_even_stones_number():
                even_rule = participants[j].get_rules_even()
                even_stones = participants[j].give_even_stones()
                participants[even_rule].add_even_stones(even_stones)

            if participants[j].get_odd_stones_number():
                odd_rule = participants[j].get_rules_odd()
                odd_stones = participants[j].give_odd_stones()
                participants[odd_rule].add_odd_stones(odd_stones)

    winner = participants[monkey_number - 1]
    for i in range(monkey_number-1):
        if participants[i].get_all_stones_number() > winner.get_all_stones_number():
            winner = participants[i]
    winner_name = winner.get_name()

    print(
        f"The winner from this game is {winner_name}, with {winner.get_all_stones_number()} coconuts!")

    # for i in range(monkey_number):
    #     print(
    #         f'o macaco {i} possui: {participants[i].get_all_stones_number()}')

    end = time.time()
    print(f"The elapsed time was {end - start} seconds.")


print("First game ------------------------- ")
Games(file_50_macacos)
print("------------------------------------ ")

print("Second game ------------------------- ")
Games(file_100_macacos)
print("------------------------------------ ")

print("Third game ------------------------- ")
Games(file_200_macacos)
print("------------------------------------ ")

print("Fourth game ------------------------- ")
Games(file_400_macacos)
print("------------------------------------ ")

print("Fifth game ------------------------- ")
Games(file_600_macacos)
print("------------------------------------ ")

print("Sixth game ------------------------- ")
Games(file_800_macacos)
print("------------------------------------ ")

print("Seventh game ------------------------- ")
Games(file_900_macacos)
print("------------------------------------ ")

print("Eighth game ------------------------- ")
Games(file_1000_macacos)
print("------------------------------------ ")
