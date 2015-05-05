package jp.freeex.fourtypes.client.q;

import java.util.ArrayList;
import java.util.List;

import jp.freeex.fourtypes.client.ClientUtils;
/**
 * 質問管理クラス。
 * @author tasuku
 */
public class QuestionManager {

	/**
	 * テスト１（注目・司令指向/法則・理想指向）問題配列。
	 * 設問、A側（注目・司令）回答、B側（法則・理想）回答の配列。
	 */
	public static final String[][] TROPISM_ARRAY = {
		{"お気に入りの服を友人に馬鹿にされたら？",
		"身に着けているのが嫌になる。",
		"友人のセンスがないと思う。"},
		{"小さい子供の頃、どちらかと言えば？",
		"大人に甘えたり、気に入られようと努力した。",
		"人目をあまり気にしない子だった。"},
		{"失敗して迷惑をかけた場合、大切なのはどっち？",
		"迷惑をかけた相手に、穴埋めをしたり、謝ったりすること。",
		"失敗した原因の改善策を見つけたり、深く考えること。"},
		{"親や先生から、どうされるとイヤ？",
		"嫌われたり、バカだと思われるのがイヤ。",
		"誤解されたり、邪魔されたりするのがイヤ。"},
		{"初対面の相手と二人きりになったら？",
		"喋りかけたり、相手の様子をみて、どういう立場の人か探る。",
		"最初は距離をおいてしまう、打ちとけるのに時間がかかる。"},
		{"転校して新しいクラスに。どんな子と仲良くなりたい？",
		"おもしろい人、楽しそうなグループにいる人。",
		"趣味があう人、落ちついてる人。"},
		{"気になる異性に対しては、どちらかというと？",
		"どうすれば相手に好きになってもらえるか考える。",
		"相手への自分の気持ちばかり考えてしまう。"},
		{"学校生活で大切なものは？",
		"一緒に楽しめる友だちや、先生からの評価、成績。",
		"学校自体にはあまり意味を感じない。ちゃんと出席すること。"},
		{"友人から、気にいらないプレゼントをもらったら？",
		"プレゼント自体は嬉しい。一応、喜んだり、お礼を言う。",
		"困る。場合によっては、いらないことをはっきり伝える。"},
		{"友だちがセンスの悪い服を着てきたとき",
		"できれば一緒に歩きたくない。友だちでないフリをする。",
		"変な服だとは思うけど、そこまで気にしない。"},
		{"友だちに裏切られたらどうする？",
		"周りや、共通の知り合いに相談する。",
		"裏切った本人に話を聞こうとする。"},
		{"遠足のバスの中で、つまらないレクリエーションがはじまり、渋々参加することになった。",
		"可能ならおもしくする。または、他に活かすために参考にする。",
		"迷惑に感じる。場合によっては空気を読まず文句をいう。"},
		{"おせじや、おだてられることについてどう思うか。",
		"おだてられるのは嫌いじゃない。実績に敬意を払うのは当然。",
		"やたらおだてられると、不安になったり、不快に感じる。"},
		{"自分のことは好き？",
		"自分が好きだったり、大事なのは当然。",
		"よくわからない。たまにすごく嫌いになる。"},
		{"親友のダメなところに対しては？",
		"言わずにゆるすのが本当の友達。",
		"本当のことを言ってあげるのが本当の友達。"}
	};

	/**
	 * テスト２（注目・司令）問題配列。
	 * 設問、A側（注目）回答、B側（司令）回答の配列。
	 */
	public static final String[][] EXTROVERT_ARRAY = {
		{"なれるとしたら、どちらがいいですか？",
		"目立ちたがり。", "仕切り屋。"},
		{"予定を決めるために、友人同士で集まったら？",
		"決まらなくても、みんなとの会話を楽しみたい。",
		"具体的な提案がでてサクサク進んでほしい。"},
		{"どちらのほうが自分を好きだと思う？",
		"愛してると言ってくれるけど何もしてくれない。",
		"好きなそぶりはあるけど、優しい言葉すらない。"},
		{"「よし、明日からは早起きするぞ！」とみんなの前で宣言した。さてどうなる？",
		"三日で忘れる。", "言った限りは守る。"},
		{"買物で迷っている時、有効な店員のひと押しの言葉とは？",
		"「お似合い」など誉めてくれる。",
		"「今売れてる」、「最後のひとつ」"},
		{"後輩からは、自分がどう思われているとわかったらショック？",
		"怖がられ、嫌われている。", "バカにされ、みくびられている。"},
		{"自分と同じジャンルの中で、自分より才能のある人とは？",
		"友だちになりたい。", "ライバルとして認められたい。"},
		{"先輩からは　同輩に比べ自分の方が",
		"可愛がってもらえると嬉しい。", "評価されると嬉しい。"},
		{"どんなプレゼントをあげたい？ ",
		"自分が、良いと思うもの。", "誰もが、価値あるとわかるもの。"},
		{"かなえたい夢へのアプローチ方法は？",
		"夢を語らなければ始まらない。",
		"実現できそうになったら、話す。"},
		{"泣いて頼まれたら？",
		"かわいそうだから、「イヤ」とは言えない。",
		"みっともないと軽蔑して、距離を置く。"},
		{"恋人とそろそろ危ないカンジ。別れるなら、どうする？",
		"別の人を好きになりフェードアウト。",
		"相手が言いだす前に切り出す。"}
	};

	/**
	 * テスト3（法則・理想）問題配列。
	 * 設問、A側（法則）回答、B側（理想）回答の配列。
	 */
	public static final String[][] INTROVERT_ARRAY = {
		{"どっちかというと？",
		"話すより聞く方が好き。",
		"聞くのも好きだけど、熱く語るのも好き。"},
		{"恋人とはどんな関係が理想？",
		"一緒にいて落ちつく関係。",
		"お互いのすべてをわかりあう関係。"},
		{"趣味にはまることがある？",
		"特になにかに熱中できない。",
		"熱く語りたい趣味がいくつかある。"},
		{"クラスの人気者や中心人物をどう思う？",
		"自分とは関係ない人。",
		"なんとなく憧れてしまう。"},
		{"こういう「性格テスト」をどう思う？",
		"鵜呑みにはしないが興味はある。",
		"人間はタイプ分けなどできないはず。"},
		{"自分のことを好きか？",
		"そんなことは考えない。",
		"時々、すごくイヤになる。"},
		{"好きな人ができた。さてどうなる？",
		"なぜ好きか、どこが好きか考える。",
		"自分の気持ちが止まらなくなる。"},
		{"小さい子供のころ、ものすごく好きなおもちゃやグッズなどがあった？",
		"特になかった。",
		"手放せず、無いと泣いたりした。"},
		{"買物で、買う・買わないの基準は？",
		"必要・無難・効率・手頃。",
		"好き・自分らしい・かっこいい。"},
		{"あなたの説明、わからないと言われるときの理由はどっち？",
		"合ってるけどミもフタもない。",
		"長くてまどろっこしい。"},
		{"ノルマ・〆切などの言葉をきくと思い浮かべるイメージは？",
		"調整・段取り・計画・工夫。",
		"管理・数字・賞罰・成果。"},
		{"あなたがすごくイヤだと感じるのは、どっち？",
		"指示ミスで仕事がムダになる。",
		"間違っていると思う事を強制される。"}
	};

	/**
	 * QuestionManagerインスタンス
	 */
	private static QuestionManager manager = new QuestionManager();

	/**
	 * デフォルトコンストラクタ。処理なし。
	 */
	private QuestionManager(){}

	/**
	 * QuestionManagerインスタンスを取得する。
	 * @return QuestionManager インスタンス
	 */
	public static final QuestionManager getInstance(){
		return manager;
	}

	/**
	 * 向性テスト質問リストを取得する。
	 * 利用者のなれをなるべく避けるため、質問のならびを呼び出しのたびに変化させている。
	 * @return List<Question> 向性テスト質問リスト
	 */
	public List<Question> getTripismQuestions(){
		return getQuestions(TROPISM_ARRAY);
	}

	/**
	 * 注目型／司令型テスト質問リストを取得する。
	 * 利用者のなれをなるべく避けるため、質問のならびを呼び出しのたびに変化させている。
	 * @return List<Question> 注目型／司令型テスト質問リスト
	 */
	public List<Question> getExtrovertQuestions(){
		return getQuestions(EXTROVERT_ARRAY);
	}

	/**
	 * 法則型／理想型テスト質問リストを取得する。
	 * 利用者のなれをなるべく避けるため、質問のならびを呼び出しのたびに変化させている。
	 * @return List<Question> 法則型／理想型テスト質問リスト
	 */
	public List<Question> getIntrovertQuestions(){
		return getQuestions(INTROVERT_ARRAY);
	}

	/**
	 * 引数で渡された質問情報を、利用者のなれをなるべく避けるため、
	 * 質問のならびを呼び出しのたびに変化させた質問リストに加工する。
	 * @param array 質問情報
	 * @return 質問リスト（毎回回答が異なる）
	 */
	private List<Question> getQuestions(String[][] array){
		List<Question> questions = new ArrayList<Question>();
		int[] orders = ClientUtils.createOrderArray(array.length);
		for(int pos=0;pos<orders.length;pos++){
			questions.add(
				new Question(
					"Q." + (pos+1) + " " +array[orders[pos]][0],
					array[orders[pos]][1],
					array[orders[pos]][2]
				)
			);
		}
		return questions;
	}
}
