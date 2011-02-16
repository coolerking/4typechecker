package com.otakingex.type4.master;

public class TestMaster20100719 implements TestMaster {

	private static final long serialVersionUID = 2734457402715240897L;

	public static final String[][] TROPISMS = {
		{"アドレス帳の登録数に関して", "多いとなんだか嬉しい", "あまり気にしたことがない"},
		{"気に入らないプレゼントをもらったとき、どう思う？", "気に入らなくても気持ちがうれしい", "ありがたいけど、喜べない"},
		{"かなえたい夢へのアプローチ方法は", "有言実行", "可能性が高くなるまで言わない"},
		{"飲み会やパーティでは", "みんなでワイワイしたい", "静に語り合いたい"},
		{"友だちの欠点に対しては", "方法を工夫して指摘する", "自分がガマンすればいいだけ"},
		{"初対面の人には", "話題がとぎれないよう、気を使う", "なにも話せず、無口になってしまう"},
		{"せっかくの休日、一日中一人でいるのは", "なんだか取り残されてるみたいで不安", "大好き！ストレスにならない"},
		{"いままで意識したこともない人から「付き合ってほしい」と言われた", "とりあえず考えてみる", "とりあえず断る"},
		{"あえて選ぶなら、親友とはどっちでしょう？", "一緒に長くすごして、いろんな思い出を共有してる人", "会った時間よりも、お互いを理解しあえている人"},
		{"友だちや恋人のセンスや容姿は", "もちろん気になる", "まったく気にならない"},
		{"好きな人ができたら、どっちが気になる？", "相手が自分をどう思っているか", "自分の気持ちが本物かどうか"},
		{"子供のころ、叱られたりケンカすると", "駄々をこね、みんなの前で大泣きした", "目立たない場所で泣いたり、すねたりした"}
	};
	
	public static final String[][] KINGSOLD = {
		{"どっちかというと？", "目立ちたがり", "仕切り屋"},
		{"泣いて頼まれたら？", "「イヤ」とは言えない", "「泣くのは愚かだなぁ」と思ってしまう"},
		{"クラスで一番才能のある人とは？", "友だちになりたい", "ライバルとして認められたい"},
		{"好きな人には", "まず自分の気持ちを伝えたい", "相手に「好き」と言わせたい"},
		{"どんなプレゼントをあげたい？", "自分があげたいもの", "誰もが納得できる、価値あるもの"},
		{"「よし、明日からは早起きするぞ！」と宣言した。さてどうなる？", "三日で忘れる", "言った限りは守る"},
		{"彼氏や彼女と別れるとき、どうする？", "別の人を好きになってフェードアウト", "相手が自分を好きなうちに別れる"},
		{"子供のころ　遊ぶ時は", "気の合う友達を誘って遊んだ", "得意な遊びに友達を誘った"}
	};
	
	public static final String[][] SCHLCRFT = {
		{"どっちかというと？", "話すより聞く方が好き", "聞くのも好きだけど、熱く語るのも好き"},
		{"恋人とはどんな関係が理想？", "一緒にいて落ちつく関係", "お互いのすべてをわかりあう関係"},
		{"趣味にはまることがある？", "特になにかに熱中できない", "熱く語りたい趣味がいくつかある"},
		{"クラスの人気者をどう思う？", "自分とは関係ない人", "なんとなく憧れてしまう"},
		{"こういう「性格テスト」をどう思う？", "当たっていると逆に警戒する", "人間はタイプ分けなんかできないはず"},
		{"自分のことを好きか？", "そんなこと考えてもわからない", "時々、すごくイヤになる"},
		{"好きな人ができた。さてどうなる？", "自分の気持ちが本当か疑う", "自分の気持ちが止まらなくなる"},
		{"子供のころ　好きな遊びが", "はっきりしていたので、違う遊びは断った", "特になかったので、誘われればつきあった"}
	};
	

	@Override
	public String[][] getKingSoldArray() {
		return KINGSOLD;
	}

	@Override
	public String[][] getSchlCrftArray() {
		return SCHLCRFT;
	}

	@Override
	public String[][] getTropismArray() {
		return TROPISMS;
	}

}