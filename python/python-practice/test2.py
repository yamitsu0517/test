# test1.py の続き。　for 使って結合

str1 = "パトカー"
str2 = "タクシー"

ans = ''
ans1 = ans.join([i + j for i,j in zip(str1, str2)])

# join : 配列を文字列に結合
# zip  : 同時に要素を取得したい時に使う。今回の場合、１つずつ抽出される。https://note.nkmk.me/python-zip-usage-for/

# 1こ飛ばして表示する　ぱ（た）と（く）か（ー）ー　＝＞パトカー
print (str1 + "と" + str2 + "の結合")
print (ans1)


