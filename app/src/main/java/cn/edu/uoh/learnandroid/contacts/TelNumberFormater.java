package cn.edu.uoh.learnandroid.contacts;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TelNumberFormater {
    static final Pattern removePattern = Pattern.compile("[^\\d\\+]+");

    static void format(Context context) {
        //获取ContentResolver
        ContentResolver contentResolver = context.getContentResolver();

        Cursor phones = contentResolver.query(ContactsContract.Data.CONTENT_URI,
                null,
                ContactsContract.Data.MIMETYPE + " = 'vnd.android.cursor.item/phone_v2' ",
                null, null);
        while (phones.moveToNext()) {
            long id = phones.getLong(phones.getColumnIndex(
                    ContactsContract.Data._ID));
            String number = phones.getString(phones.getColumnIndex(
                    ContactsContract.Data.DATA1));
            String newNumber = formatNumber(number);
            if (number.equals(newNumber)) {
                continue;
            }
            update(contentResolver, id, newNumber);
        }
        phones.close();
    }

    private static void update(ContentResolver contentResolver, long id, String number) {
        ContentValues values = new ContentValues();
        values.put("data1", number);
        contentResolver.update(ContactsContract.Data.CONTENT_URI,
                values,
                ContactsContract.Data._ID + "=?",
                new String[]{"" + id});
    }

    private static String formatNumber(String number) {
        // 删除除+和数字之外的所有字符
        Matcher removeMatcher = removePattern.matcher(number);
        String newNum = removeMatcher.replaceAll("");

        for(FormatPattern fp : FormatPattern.formatPatterns) {
            Matcher m = fp.matcherPattern.matcher(newNum);
            if (m.matches()) {
                return m.replaceAll(fp.replace);
            }
        }
        // 如果未格式化，返回输入的值
        return number;
    }
}
