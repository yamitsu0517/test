#  Progate にあるような、注文表を作成するアプリを目指せ。

import menu

number = input("年齢を入力してください:",)

print("あなたの年齢は" + str(number) + "歳ですね。了解です")

if int(number) < 20:
    print(number + "歳なので未成年ですね")
else:
    print(number + "歳なので成人ですね。お酒を飲みましょう")

menuClass = menu.menu()

roop = True
while roop:
    # 注文確認
    flg = menuClass.menu()
    if (flg == False):
        continue

    if enough == 'yes' or enough == 'no':
        if flg == False:
            continue;
        if enough == 'yes':
            roop = False
        else:
            continue;
    else:
        print('yes or no を入力してください')

