package com.example.arti_scream.sqllaws;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity  implements OnClickListener {

    static final String LOG_TAG = "myLogs";

    Button btnAdd, btnRead, btnClear, btnUpdate, btnDelete;
    EditText etNumrozd, etRozd, etNumstat, etStat, etId;

    DBHelper dbHelper;

    public String[] statti = {
            "Україна є суверенна і незалежна, демократична, соціальна, правова держава.",
            "Суверенітет України поширюється на всю її територію. Україна є унітарною державою. Територія України в межах існуючого кордону є цілісною і недоторканною.",
            "Людина, її життя і здоров'я, честь і гідність, недоторканність і безпека визнаються в Україні найвищою соціальною цінністю. Права і свободи людини та їх гарантії визначають зміст і спрямованість діяльності держави. Держава відповідає перед людиною за свою діяльність. Утвердження і забезпечення прав і свобод людини є головним обов'язком держави.",
            "В Україні існує єдине громадянство. Підстави набуття і припинення громадянства України визначаються законом.",
            "Україна є республікою. Носієм суверенітету і єдиним джерелом влади в Україні є народ. Народ здійснює владу безпосередньо і через органи державної влади та органи місцевого самоврядування.Право визначати і змінювати конституційний лад в Україні належить виключно народові і не може бути узурповане державою, її органами або посадовими особами. Ніхто не може узурпувати державну владу.",
            "Державна влада в Україні здійснюється на засадах її поділу на законодавчу, виконавчу та судову. Органи законодавчої, виконавчої та судової влади здійснюють свої повноваження у встановлених цією Конституцією межах і відповідно до законів України.",
            "В Україні визнається і гарантується місцеве самоврядування.",
            "В Україні визнається і діє принцип верховенства права. Конституція України має найвищу юридичну силу. Закони та інші нормативно-правові акти приймаються на основі Конституції України і повинні відповідати їй. Норми Конституції України є нормами прямої дії. Звернення до суду для захисту конституційних прав і свобод людини і громадянина безпосередньо на підставі Конституції України гарантується.",
            "Чинні міжнародні договори, згода на обов'язковість яких надана Верховною Радою України, є частиною національного законодавства України. Укладення міжнародних договорів, які суперечать Конституції України, можливе лише після внесення відповідних змін до Конституції України.",
            "Державною мовою в Україні є українська мова.Держава забезпечує всебічний розвиток і функціонування української мови в усіх сферах суспільного життя на всій території України. В Україні гарантується вільний розвиток, використання і захист російської, інших мов національних меншин України. Держава сприяє вивченню мов міжнародного спілкування. Застосування мов в Україні гарантується Конституцією України та визначається законом.",
            "Держава сприяє консолідації та розвиткові української нації, її історичної свідомості, традицій і культури, а також розвиткові етнічної, культурної, мовної та релігійної самобутності всіх корінних народів і національних меншин України.",
            "Україна дбає про задоволення національно-культурних і мовних потреб українців, які проживають за межами держави.",
            "Земля, її надра, атмосферне повітря, водні та інші природні ресурси, які знаходяться в межах території України, природні ресурси її континентального шельфу, виключної (морської) економічної зони є об'єктами права власності Українського народу. Від імені Українського народу права власника здійснюють органи державної влади та органи місцевого самоврядування в межах, визначених цією Конституцією. Кожний громадянин має право користуватися природними об'єктами права власності народу відповідно до закону. Держава забезпечує захист прав усіх суб'єктів права власності і господарювання, соціальну спрямованість економіки. Усі суб'єкти права власності рівні перед законом.",
            "Земля є основним національним багатством, що перебуває під особливою охороною держави. Право власності на землю гарантується. Це право набувається і реалізується громадянами, юридичними особами та державою виключно відповідно до закону.",
            "Суспільне життя в Україні ґрунтується на засадах політичної, економічної та ідеологічної багатоманітності. Жодна ідеологія не може визнаватися державою як обов'язкова. Цензура заборонена. Держава гарантує свободу політичної діяльності, не забороненої Конституцією і законами України.",
            "Забезпечення екологічної безпеки і підтримання екологічної рівноваги на території України, подолання наслідків Чорнобильської катастрофи - катастрофи планетарного масштабу, збереження генофонду Українського народу є обов'язком держави.",
            "Захист суверенітету і територіальної цілісності України, забезпечення її економічної та інформаційної безпеки є найважливішими функціями держави, справою всього Українського народу. Оборона України, захист її суверенітету, територіальної цілісності і недоторканності покладаються на Збройні Сили України. Забезпечення державної безпеки і захист державного кордону України покладаються на відповідні військові формування та правоохоронні органи держави, організація і порядок діяльності яких визначаються законом. Збройні Сили України та інші військові формування ніким не можуть бути використані для обмеження прав і свобод громадян або з метою повалення конституційного ладу, усунення органів влади чи перешкоджання їх діяльності. Держава забезпечує соціальний захист громадян України, які перебувають на службі у Збройних Силах України та в інших військових формуваннях, а також членів їхніх сімей. На території України забороняється створення і функціонування будь-яких збройних формувань, не передбачених законом. На території України не допускається розташування іноземних військових баз.",
            "Зовнішньополітична діяльність України спрямована на забезпечення її національних інтересів і безпеки шляхом підтримання мирного і взаємовигідного співробітництва з членами міжнародного співтовариства за загальновизнаними принципами і нормами міжнародного права.",
            "Правовий порядок в Україні ґрунтується на засадах, відповідно до яких ніхто не може бути примушений робити те, що не передбачено законодавством. Органи державної влади та органи місцевого самоврядування, їх посадові особи зобов'язані діяти лише на підставі, в межах повноважень та у спосіб, що передбачені Конституцією та законами України.",
            "Державними символами України є Державний Прапор України, Державний Герб України і Державний Гімн України. Державний Прапор України - стяг із двох рівновеликих горизонтальних смуг синього і жовтого кольорів. Великий Державний Герб України встановлюється з урахуванням малого Державного Герба України та герба Війська Запорізького законом, що приймається не менш як двома третинами від конституційного складу Верховної Ради України. Головним елементом великого Державного Герба України є Знак Княжої Держави Володимира Великого (малий Державний Герб України).Державний Гімн України - національний гімн на музику М. Вербицького із словами, затвердженими законом, що приймається не менш як двома третинами від конституційного складу Верховної Ради України.Опис державних символів України та порядок їх використання встановлюються законом, що приймається не менш як двома третинами від конституційного складу Верховної Ради України.Столицею України є місто Київ."


    };


    int numrozdil[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
    String[] rozdil = {"ЗАГАЛЬНІ ЗАСАДИ", "ПРАВА, СВОБОДИ ТА ОБОВ'ЯЗКИ ЛЮДИНИ І ГРОМАДЯНИНА","ВИБОРИ. РЕФЕРЕНДУМ",
                       "ВЕРХОВНА РАДА УКРАЇНИ", "ПРЕЗИДЕНТ УКРАЇНИ", "КАБІНЕТ МІНІСТРІВ УКРАЇНИ. ІНШІ ОРГАНИ ВИКОНАВЧОЇ ВЛАДИ",
                       "ПРОКУРАТУРА", "ПРАВОСУДДЯ", "ТЕРИТОРІАЛЬНИЙ УСТРІЙ УКРАЇНИ", "АВТОНОМНА РЕСПУБЛІКА КРИМ", "МІСЦЕВЕ САМОВРЯДУВАННЯ",
                        "КОНСТИТУЦІЙНИЙ СУД УКРАЇНИ", "ВНЕСЕННЯ ЗМІН ДО КОНСТИТУЦІЇ УКРАЇНИ", "ПРИКІНЦЕВІ ПОЛОЖЕННЯ", "ПЕРЕХІДНІ ПОЛОЖЕННЯ"
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.add);
        btnAdd.setOnClickListener(this);

        btnRead = (Button) findViewById(R.id.read);
        btnRead.setOnClickListener(this);

        btnClear = (Button) findViewById(R.id.clear);
        btnClear.setOnClickListener(this);

        btnUpdate = (Button) findViewById(R.id.update);
        btnUpdate.setOnClickListener(this);

        btnDelete = (Button) findViewById(R.id.delete);
        btnDelete.setOnClickListener(this);

        etNumrozd = (EditText) findViewById(R.id.numrozd);
        etRozd = (EditText) findViewById(R.id.rozd);
        etNumstat = findViewById(R.id.numstat);
        etStat = findViewById(R.id.stat);
        etId = (EditText) findViewById(R.id.id);


        // создаем объект для создания и управления версиями БД
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor c = db.query("Laws", null, null, null, null, null,null );

        if (c.getCount() == 0) {

            ContentValues cvc = new ContentValues();

            for (int i =0; i<20; i++ ){
                cvc.put("numrozd",numrozdil[0] );
                cvc.put("rozd",rozdil[0]);
                cvc.put("numstat",i+1);
                cvc.put("stat", statti[i] );
                db.insert("Laws",null,cvc);
            }

        }
        c.close();


    }



    @Override
    public void onClick(View v) {



        // создаем объект для данных
        ContentValues cv = new ContentValues();

        Integer numrozd;
        Integer numstat;
        // получаем данные из полей ввода
        try {numrozd = Integer.parseInt(etNumrozd.getText().toString());} catch(NumberFormatException e){ numrozd = 0;};
        String rozd = etRozd.getText().toString();
        try {numstat = Integer.parseInt(etNumstat.getText().toString());} catch(NumberFormatException e){ numstat = 0;};
        String stat = etStat.getText().toString();
        String id = etId.getText().toString();

        // подключаемся к БД
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        switch (v.getId()) {
            case R.id.add:
                Log.d(LOG_TAG, "--- Insert in mytable: ---");
                // подготовим данные для вставки в виде пар: наименование столбца - значение

                cv.put("numrozd", numrozd);
                cv.put("rozd", rozd);
                cv.put("numstat",numstat);
                cv.put("stat",stat);

                // вставляем запись и получаем ее ID
                long rowID = db.insert("Laws", null, cv);
                Log.d(LOG_TAG, "row inserted, ID = " + rowID);
                break;
            case R.id.read:

                Log.d(LOG_TAG, "--- Rows in table Laws: ---");
                // делаем запрос всех данных из таблицы mytable, получаем Cursor
                Cursor c = db.query("Laws", null, null, null, null, null, null);

                // ставим позицию курсора на первую строку выборки
                // если в выборке нет строк, вернется false
                if (c.moveToFirst()) {

                    // определяем номера столбцов по имени в выборке
                    int idColIndex = c.getColumnIndex("id");
                    int numrozdColIndex = c.getColumnIndex("numrozd");
                    int rozdColIndex = c.getColumnIndex("rozd");
                    int numstatColIndex = c.getColumnIndex("numstat");
                    int statColIndex = c.getColumnIndex("stat");

                    do {
                        // получаем значения по номерам столбцов и пишем все в лог
                        Log.d(LOG_TAG,
                                "ID = " + c.getInt(idColIndex) +
                                        ", numrozd = " + c.getString(numrozdColIndex) +
                                        ", rozd = " + c.getString(rozdColIndex) +
                                        ", numstat = " + c.getString(numstatColIndex) +
                                        ", stat = " + c.getString(statColIndex)
                        );
                        // переход на следующую строку
                        // а если следующей нет (текущая - последняя), то false - выходим из цикла
                    } while (c.moveToNext());
                } else {
                    Log.d(LOG_TAG, "0 rows");
                }
                c.close();


                Intent intent = new Intent(MainActivity.this, DisplayLaws.class);
                startActivity(intent);


                break;
            case R.id.clear:
                Log.d(LOG_TAG, "--- Clear Laws: ---");
                // удаляем все записи
                int clearCount = db.delete("Laws", null, null);
                Log.d(LOG_TAG, "deleted rows count = " + clearCount);
                Log.d(LOG_TAG, "To create base again reenter the app");
                break;

            case R.id.update:
                if (id.equalsIgnoreCase("")) {
                    break;
                }
                Log.d(LOG_TAG, "--- Update Laws: ---");
                // подготовим значения для обновления
                cv.put("numrozd", numrozd);
                cv.put("rozd", rozd);
                cv.put("numstat", numstat);
                cv.put("stat",stat);
                // обновляем по id
                int updCount = db.update("Laws", cv, "id = ?",
                        new String[] { id });
                Log.d(LOG_TAG, "updated rows count = " + updCount);
                break;
            case R.id.delete:
                if (id.equalsIgnoreCase("")) {
                    break;
                }
                Log.d(LOG_TAG, "--- Delete from Laws: ---");
                // удаляем по id
                int delCount = db.delete("Laws", "id = " + id, null);
                Log.d(LOG_TAG, "deleted rows count = " + delCount);
                break;




        }
        // закрываем подключение к БД
        dbHelper.close();
    }



         public static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            // конструктор суперкласса
            super(context, "LawsDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, "--- onCreate database ---");
            // создаем таблицу с полями
            db.execSQL("create table Laws ("
                    + "id integer primary key autoincrement,"
                    + "numrozd integer,"
                    + "rozd text,"
                    + "numstat integer,"
                    + "stat text"
                    + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }


    public void DisplayLaws(View view){
        startActivity(new Intent(this,DisplayLaws.class));



    }


}
