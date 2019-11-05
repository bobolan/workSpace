package com.example.roomwordsample;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Word.class}, version = 1)
public abstract class WordRoomDatabase extends RoomDatabase {
    public abstract WordDao wordDao();
    private static volatile WordRoomDatabase instance;
    static WordRoomDatabase getDatabase(final Context context) {
        if(instance == null) {
            synchronized(WordRoomDatabase.class) {
                if(instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database")
                            .addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return instance;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(instance).execute();

        }

    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final WordDao mDao;
        PopulateDbAsync(WordRoomDatabase db) {
            mDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(Void... params) {
            mDao.deleteAll();
            Word word = new Word( "Hello");
            mDao.insert(word);
            word = new Word( "World");
            mDao.insert(word);
            return null;
        }
    }
}
