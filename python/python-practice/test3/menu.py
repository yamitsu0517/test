

class menu:

    def __init__(self):
        
        self.menuList  = {'ハンバーガー', 'ポテト', 'ナゲット'}
        self.priceList = {'ハンバーガー' : 450, 'ポテト' : 200, 'ナゲット' : 500}


    def menu(self):
        text_flg = True
        order = input('注文をお願いします:',)
        count = int(input('いくつですか:',))
        if (order in self.menuList):
            print ('承知しました' + order + 'が' + count + '点')
            while text_flg:
                enough = input('注文は以上でよろしいでしょうか(yes or no):',)
                if enough == 'yes' or enough == 'no':
                    return True
                else:
                    print ('yes or no で入力してください')
        else:
            print ('申し訳ございません。そちらの商品はお取り扱いしておりません')
