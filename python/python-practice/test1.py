str = 'stressed';
ans = str[::-1]

print("default")
print(str)

print("sty[::-1]")
# 逆になって（後ろから）表示される
print (ans)

ans2 = str[0:2]

print("sty[0:2]")
# 0番目から1(2-1)番目が表示される
print (ans2)

ans3 = str[:-1]

print("str[:-1]")
# -1 番目を取り除く
print (ans3)

print("###########################")

str1 = "パタトクカシーー"
print("default:" + str1) 


ans4_1 = str1[::1]
print ("str[::1]")
# 一文字も飛ばさない => パタトクカシーー
print (ans4_1)


ans4 = str1[::2]
print ("str[::2]")
# 1こ飛ばして表示する　ぱ（た）と（く）か（ー）ー　＝＞パトカー
print (ans4)


ans5 = str1[::3]
# 2こ飛ばして表示する　ぱ（たと）く（かー）ー　＝＞パクー
print ("str[::3]")
print (ans5)

