package com.bishe.me;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import com.bishe.me.bean.PinglunBean;
import com.bishe.me.user.LoginActivity;
import java.util.List;

public class Activity2jingdian extends BaseActivity{
	
   public static int TAG=1;
	LinearLayout linearLayout;
	List<PinglunBean> pinglunBeans;
	TextView t1,t2,t3;
	EditText editTexts;
	Button button1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity2jingdian);
		String s=getIntent().getStringExtra("name");
		editTexts=(EditText)findViewById(R.id.editText1);


		t1=(TextView)findViewById(R.id.textView1);
		t2=(TextView)findViewById(R.id.textView2);
		t3=(TextView)findViewById(R.id.textView3);
		button1=(Button)findViewById(R.id.button1);
		ImageView imageView=(ImageView)findViewById(R.id.img11);
		
		linearLayout=(LinearLayout)findViewById(R.id.l3334);
		
		String[] titleStrings={"昆明老街"
				,"翠湖公园"
				,"世博园"
				,"民族村"
				,"石林"
				,"苍山"};
		String[] neirongs={"“昆明老街”东起正义路、西至云瑞西路（市府东街)、南起景星街、北至人民中路。老街区包括文明街、文庙直街、甬道街、光华街、文庙、胜利堂、景星花鸟市场等街巷和旅游景点，是昆明这座历史文化名城唯一保留下来的一片原汁原味的老街区，经历了900多年世事沧桑和文化积淀，蕴涵了过去岁月的丰富信息。若游客喜欢历史建筑，不妨来昆明老街走走逛逛，带你走回往日岁月。"
				,"昆明翠湖公园位于昆明市区的北部螺峰山下，云南大学正门对面，是市区最漂亮的公园之一。虽然面积不大，但很有特色。这里最初曾是滇池中的一个湖湾，后来因水位下降而成为一汪清湖。自明朝起历任云南行政官员都曾在这里修亭建楼。由于垂柳和碧水构成其主要特色的缘故，本世纪初正式定名为翠湖。它以“翠堤春晓”而闻名四方。人们称之为“镶嵌在昆明城里的一颗绿宝石”。园内，纵贯南北的阮堤（1834年云贵总督阮元拨款所筑），直通东西的唐堤（1919年由时任孙中山的滇川黔三省建国联军总司令的唐继尧拨款所筑)，将翠湖分成五片景区;湖心岛景区以湖心亭和观鱼楼等清代建筑为主;东南面是水月轩和金鱼岛;东北面是竹林岛和九龙池;南边是葫芦岛和九曲桥;西边是海心亭。"
				,"昆明世界园艺博览园（简称世博园）是1999昆明世界园艺博览会会址，设在昆明东北郊的金殿风景名胜区，距昆明市区约4公里。博览园占地面积约218公顷，植被覆盖率达76.7%，其中有120公顷灌木丛茂密的缓坡，水面占10%~15%。园区整体规划依山就势，集全国各省、区、市地方特色和95个国家风格的园林园艺品，庭院建筑和科技成就于一园，体现了“人与自然，和谐发展”的时代主题，是一个具有“云南特色、中国气派、世界一流”的园林园艺品大观园。2016年8月3日，国家旅游局批准云南省昆明市昆明世博园景区为5A级景区。"
				,"　民族村位于昆明市南6公里，占地2000亩，以反映和展示云南26个民族的风土人情，以自然村落式民族民居建筑为特色。游客可在村寨中尽情参观园林景观，领略少数民族的民居、民宅、民俗、民情及文化、音乐、歌舞、宗教等。。"
				,"石林风景区( Stone Forest Scenic ) :世界自然遗产，世界地质公园，国家AAAAA级旅游景区，国家重点风景名胜区，国家地质公园，全国文明风景旅游区，最佳资源保护中国十大风景名胜区。石林形成于2.7亿年前，是世界喀斯特地貌的精华，拥有世界上喀斯特地貌演化历史最久远、分布面积最广、类型齐全、形态独特的古生代岩溶地貌群落，被誉为“天下第一奇观”。风景区由石林、黑松岩(乃古石林）、飞龙瀑（大叠水）、长湖、圭山、月湖、奇风洞等组成，以雄、奇、险、秀、幽、奥、旷著称。石林风景区范围广袤，山光水色各具特色，石牙、峰丛、溶丘、溶洞、溶蚀湖、瀑布、地下河等景观错落有致，是最适宜人居的生态环境，是最佳旅游目的地，是旅游者的天堂。"
				,"苍山雪”是大理风花雪月四景之一。如果说洱海是大理的魂，那么苍山就是大理的魄。洱海灵秀柔情，苍山巍峨雄壮，刚柔并济共同形成了大理包容醇厚的性情。苍山，十九峰十八溪，一峰一景，一溪一观。来到大理，不登一次苍山，大理之行便是不完整的。"};

		switch (TAG) {
		case 1:
			t1.setText(titleStrings[TAG-1]);
			t2.setText(neirongs[TAG-1]);
			imageView.setImageResource(R.drawable.jd1);
			break;
		case 2:
			t1.setText(titleStrings[TAG-1]);
			t2.setText(neirongs[TAG-1]);
			imageView.setImageResource(R.drawable.jd2);
			break;
			
		case 3:
			t1.setText(titleStrings[TAG-1]);
			t2.setText(neirongs[TAG-1]);
			imageView.setImageResource(R.drawable.jd3);
			break;
			
		case 4:
			t1.setText(titleStrings[TAG-1]);
			t2.setText(neirongs[TAG-1]);
			imageView.setImageResource(R.drawable.jd4);
			break;
			
		case 5:
			t1.setText(titleStrings[TAG-1]);
			t2.setText(neirongs[TAG-1]);
			imageView.setImageResource(R.drawable.jd5);
			break;
			
		case 6:
			t1.setText(titleStrings[TAG-1]);
			t2.setText(neirongs[TAG-1]);
			imageView.setImageResource(R.drawable.jd6);
			break;

		default:
			break;
		}
		
	
		linearLayout=(LinearLayout)findViewById(R.id.l3334);
		
		//linearLayout.removeAllViews();
		
	
		
//		denglu();
		
		
		
		button1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PinglunBean mPinglunBean=new PinglunBean();
				mPinglunBean.setUser(LoginActivity.user);
				mPinglunBean.setName(editTexts.getText().toString());
				mPinglunBean.setLike(TAG+"");
				
				showProgressDialog("评论中", "请稍后");
				mPinglunBean.save(new SaveListener<String>() {
				     @Override
				     public void done(String objectId,BmobException e) {
				         if(e==null){
				             showTost("评论成功");
				             dissProgressDialog();
				             linearLayout.removeAllViews();
//				             denglu();
				            
				         }else{
				        	 showTost("评论失败：" + e.getMessage());
				        	  dissProgressDialog();
				         }
				     }

				
				 });
				
				
				
				//addTypeView(LoginActivity.user+"\n"+editTexts.getText().toString(),"2");
				//LoginActivity.pingluns.add(bookBean.title+"\n"+StringUtil.getTimeAll()+"\n"+StringUtil.getTimeAll()+"\n"+editTexts.getText().toString());
			}
		});
		}
	 
	
	 private void addTypeView( String name,String s) {

	        View view = LayoutInflater.from(this).inflate(R.layout.item_text, null);
	        TextView tvName = (TextView) view.findViewById(R.id.t1);
	        tvName.setText(name+"");
	        
	        view.setTag(s);
	        view.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//xijshowTost(v.getTag()+"");
					
				}
			});
	        linearLayout.addView(view);
	    }
	 
	 
	 public void denglu() {
			
		 pinglunBeans=null;
			showProgressDialog("查询中", "请稍后");
			BmobQuery<PinglunBean> query = new BmobQuery<PinglunBean>();
			query.addWhereEqualTo("like", ""+TAG);
			//查询playerName叫“比目”的数据
			//query.addWhereEqualTo("playerName", "比目");
			//返回50条数据，如果不加上这条语句，默认返回10条数据
			query.setLimit(500);
			//执行查询方法
			query.findObjects(new FindListener<PinglunBean>() {
			    @Override
			    public void done(List<PinglunBean> object, BmobException e) {
			        if(e==null){
			           // toast("查询成功：共"+object.size()+"条数据。");
			           
			               //获得playerName的信息
			            //   gameScore.getPlayerName();
			               //获得数据的objectId信息
			            	//showTost("查询成功");
			            	 dissProgressDialog();
			            	 pinglunBeans=object;
			            	
			            	 for (int j = 0; j < pinglunBeans.size(); j++) {
			            		 addTypeView(
			            				 pinglunBeans.get(j).getUser()+"\n"+pinglunBeans.get(j).getCreatedAt()+"\n"+pinglunBeans.get(j).getName()
			            				 
			            				 , "");
							}
			        }else{
//			        	 showTost("查询失败");
			            Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
			            dissProgressDialog();
			        }
			        dissProgressDialog();
			    }
			
			});
			
		}

	 public static void into(Context context){
	        Intent intent =new Intent(context,Activity2jingdian.class);
	        context.startActivity(intent);
	    }

}
