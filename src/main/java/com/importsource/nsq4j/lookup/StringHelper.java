package com.importsource.nsq4j.lookup;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class StringHelper
{
  public static final char[] CHARACTERS = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

  private static LazyInit lock = new LazyInit();
  private static SecureRandom prng = null;

  public static void main(String[] paramArrayOfString)
  {
  }

  public static String join(Collection paramCollection, String paramString)
  {
    return joinCol(paramCollection, paramString);
  }

  public static String join(List paramList, String paramString)
  {
    return joinCol(paramList, paramString);
  }

  private static String joinCol(Collection paramCollection, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (Iterator localIterator = paramCollection.iterator(); localIterator.hasNext(); ) { Object localObject = localIterator.next();
      if (localObject != null)
        localStringBuilder.append(localObject.toString());
      localStringBuilder.append(paramString);
    }

    localStringBuilder.delete(localStringBuilder.length() - paramString.length(), localStringBuilder.length());
    return localStringBuilder.toString();
  }

  public static String underscoreSpaces(String paramString)
  {
    if (paramString == null)
      return null;
    String str = paramString.trim().replaceAll("\\s+", "_");
    str = str.replaceAll("_+", "_");
    return str;
  }

  public static String reverse(String paramString) {
    StringBuffer localStringBuffer = new StringBuffer(paramString);
    return localStringBuffer.reverse().toString();
  }

  public static String capitalize(String paramString)
  {
    if ((paramString == null) || (paramString.isEmpty())) {
      return paramString;
    }
    return new StringBuilder().append(paramString.substring(0, 1).toUpperCase()).append(paramString.substring(1)).toString();
  }

  public static String uncapitalize(String paramString)
  {
    if ((paramString == null) || (paramString.isEmpty()))
      return paramString;
    return new StringBuilder().append(paramString.substring(0, 1).toLowerCase()).append(paramString.substring(1)).toString();
  }

  public static String capitalizeWords(String paramString) {
    String[] arrayOfString1 = paramString.split("\\s+");
    String str1 = "";

    for (String str2 : arrayOfString1) {
      str1 = new StringBuilder().append(str1).append(capitalize(str2)).append(" ").toString();
    }
    return str1.trim();
  }

  public static String singularize(String paramString) {
    String str = paramString;
    if (str.endsWith("s"))
      str = str.substring(0, str.length() - 1);
    return str;
  }

  public static boolean like(String paramString1, String paramString2)
  {
    String str1 = prepareStringSearch(paramString1);
    String str2 = prepareStringSearch(paramString1);
    return str1.equals(str2);
  }

  private static String prepareStringSearch(String paramString)
  {
    String str = new StringBuilder().append(" ").append(paramString.toLowerCase()).toString();
    str = str.replaceAll("\\s\\&\\s", " and ");
    str = str.replaceAll("[\\,\\-]", " ");
    str = str.replaceAll("[^a-z0-9\\s]", "");
    str = str.replaceAll("\\sthe\\s", " ");
    str = str.replaceAll("\\sa\\s", " ");
    str = str.replaceAll("\\sto\\s", " ");
    str = str.trim().replaceAll("\\s+", " ");
    return str;
  }

  public static boolean contains(String paramString1, String paramString2, boolean paramBoolean)
  {
    String str1 = new StringBuilder().append(paramBoolean ? " " : "").append(prepareStringSearch(paramString1)).append(paramBoolean ? " " : "").toString();
    String str2 = new StringBuilder().append(paramBoolean ? " " : "").append(prepareStringSearch(paramString2)).append(paramBoolean ? " " : "").toString();
    return str1.indexOf(str2) != -1;
  }

  public static String getRandomString(int paramInt)
  {
    Random localRandom = new Random();
    char[] arrayOfChar = new char[paramInt];
    for (int i = 0; i < paramInt; i++) {
      arrayOfChar[i] = CHARACTERS[localRandom.nextInt(CHARACTERS.length)];
    }
    return new String(arrayOfChar);
  }

  public static String toHex(byte[] paramArrayOfByte) {
    StringBuffer localStringBuffer = new StringBuffer();
    for (int i = 0; i < paramArrayOfByte.length; i++) {
      String str = Integer.toHexString(0xFF & paramArrayOfByte[i]);
      if (str.length() == 1)
      {
        localStringBuffer.append('0');
      }
      localStringBuffer.append(str);
    }
    return localStringBuffer.toString();
  }

  public static String shuffle(String paramString)
  {
    if (paramString.length() <= 1) {
      return paramString;
    }
    int i = paramString.length() / 2;

    String str1 = shuffle(paramString.substring(0, i));
    String str2 = shuffle(paramString.substring(i));

    if (Math.random() > 0.5D) {
      return new StringBuilder().append(str1).append(str2).toString();
    }
    return new StringBuilder().append(str2).append(str1).toString();
  }

  public static String randomString(int paramInt)
  {
    return getRandomString(paramInt);
  }

  public static String getRandomNumberString(int paramInt) {
    Random localRandom = new Random();
    char[] arrayOfChar = new char[paramInt];
    for (int i = 0; i < paramInt; i++) {
      arrayOfChar[i] = CHARACTERS[(51 + localRandom.nextInt(10))];
    }
    return new String(arrayOfChar);
  }

  public static String javascriptEscape(String paramString)
  {
    if (paramString == null)
      return null;
    String str = paramString.replaceAll("'", "\\'");
    str = str.replaceAll("\"", "\\\"");
    return str;
  }

  public static String camelize(String paramString)
  {
    return camelize(paramString, false);
  }

  public static String camelize(String paramString, boolean paramBoolean)
  {
    if (paramString == null)
      return null;
    String[] arrayOfString = paramString.split("[\\s_]+");
    StringBuffer localStringBuffer = new StringBuffer();

    if (paramBoolean)
      localStringBuffer.append(new StringBuilder().append(arrayOfString[0].substring(0, 1).toLowerCase()).append(arrayOfString[0].substring(1)).toString());
    else {
      localStringBuffer.append(capitalize(arrayOfString[0]));
    }
    for (int i = 1; i < arrayOfString.length; i++) {
      localStringBuffer.append(capitalize(arrayOfString[i]));
    }
    return localStringBuffer.toString();
  }

  public static String uncamelize(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (char c : paramString.toCharArray()) {
      if (Character.isUpperCase(c))
        localStringBuilder.append(' ');
      localStringBuilder.append(c);
    }
    return localStringBuilder.toString().trim();
  }

  public static boolean contains(String paramString, String[] paramArrayOfString)
  {
    if ((paramString == null) || (paramArrayOfString == null)) {
      return false;
    }
    for (String str : paramArrayOfString) {
      if (paramString.contains(str))
        return true;
    }
    return false;
  }

  public static boolean equals(String paramString, boolean paramBoolean, String[] paramArrayOfString)
  {
    if ((paramString == null) || (paramArrayOfString == null)) {
      return false;
    }
    for (String str : paramArrayOfString) {
      if (paramBoolean) {
        if (paramString.equalsIgnoreCase(str)) {
          return true;
        }
      }
      else if (paramString.equals(str)) {
        return true;
      }
    }
    return false;
  }

  public static String toPrettyString(Map paramMap)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("{");
    for (Iterator localIterator = paramMap.keySet().iterator(); localIterator.hasNext(); ) { Object localObject1 = localIterator.next();
      localStringBuffer.append(localObject1.toString());
      localStringBuffer.append(" : ");

      Object localObject2 = paramMap.get(localObject1);
      if (localObject2 == null)
        localStringBuffer.append("null");
      else if ((localObject2 instanceof String[]))
        localStringBuffer.append(toPrettyString((String[])localObject2));
      else {
        localStringBuffer.append(localObject2.toString());
      }
      localStringBuffer.append(", ");
    }
    if (localStringBuffer.length() > 1) {
      localStringBuffer.deleteCharAt(localStringBuffer.length() - 1);
      localStringBuffer.deleteCharAt(localStringBuffer.length() - 1);
    }
    localStringBuffer.append("}");
    return localStringBuffer.toString();
  }

  public static String toPrettyString(String[] paramArrayOfString) {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("[");
    for (String str : paramArrayOfString) {
      localStringBuffer.append(str);
      localStringBuffer.append(",");
    }
    if (localStringBuffer.length() > 1);
    localStringBuffer.deleteCharAt(localStringBuffer.length() - 1);
    localStringBuffer.append("]");
    return localStringBuffer.toString();
  }

  public static String trimChars(String paramString, int paramInt)
  {
    if (paramString == null)
      return null;
    if (paramInt >= paramString.length())
      return "";
    return paramString.substring(0, paramString.length() - paramInt);
  }

  public static String trim(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null) || (paramString2.isEmpty())) {
      return paramString1;
    }
    String str = paramString1;
    while (str.startsWith(paramString2)) {
      str = str.substring(paramString2.length());
    }
    while (str.endsWith(paramString2)) {
      str = str.substring(0, str.length() - paramString2.length());
    }
    return str;
  }

  public static String maxChars(String paramString, int paramInt)
  {
    if (paramString == null)
      return null;
    if (paramInt >= paramString.length())
      return paramString;
    return paramString.substring(0, paramInt);
  }

  public static String removeAll(String paramString, Collection paramCollection)
  {
    String str = paramString;
    for (Iterator localIterator = paramCollection.iterator(); localIterator.hasNext(); ) { Object localObject = localIterator.next();
      str = str.replace(localObject.toString(), "");
    }
    return str;
  }

  public static String removeAll(String paramString, String[] paramArrayOfString)
  {
    String str1 = paramString;
    for (String str2 : paramArrayOfString) {
      str1 = str1.replace(str2, "");
    }
    return str1;
  }

  public static String removeAll(String paramString, char[] paramArrayOfChar)
  {
   /* if (paramString == null) {
      return null;
    }
    HashSet localHashSet = new HashSet();
    for (char c : paramArrayOfChar) {
      localHashSet.add(Character.valueOf(c));
    }
    StringBuilder sb= new StringBuilder();
    StringCharacterIterator localStringCharacterIterator = new StringCharacterIterator(paramString);
    int k;
    for (??? = localStringCharacterIterator.first(); ??? != 65535; k = localStringCharacterIterator.next())
      if (!localHashSet.contains(Character.valueOf(???)))
      {
        ((StringBuilder)???).append(???);
      }
    return ((StringBuilder)???).toString();*/
	  return null;
  }

  public static boolean matches(String paramString, String[] paramArrayOfString)
  {
    if (paramString == null)
      return false;
    for (String str : paramArrayOfString) {
      if (paramString.equals(str))
        return true;
    }
    return false;
  }

  public static List<String> split(String paramString, String[] paramArrayOfString)
  {
    if (paramString == null)
      return null;
    String str1 = paramString;
    ArrayList localArrayList = new ArrayList();

    String str2 = paramArrayOfString[0];

    for (int i = 1; i < paramArrayOfString.length; i++) {
      str1 = str1.replaceAll(paramArrayOfString[i], str2);
    }
    String[] arrayOfString = str1.split(str2);
    for (int j = 0; j < arrayOfString.length; j++) {
      localArrayList.add(arrayOfString[j].trim());
    }
    return localArrayList;
  }

  public static String sanitizeUrl(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    StringCharacterIterator localStringCharacterIterator = new StringCharacterIterator(paramString);
    int j;
    for (int i = localStringCharacterIterator.first(); i != 65535; j = localStringCharacterIterator.next())
      if (Character.isLetterOrDigit(i)) {
        localStringBuilder.append(i);
      }
      else
      {
        switch (i) {
        case 36:
          localStringBuilder.append(i);
          break;
        case 45:
          localStringBuilder.append(i);
          break;
        case 95:
          localStringBuilder.append(i);
          break;
        case 46:
          localStringBuilder.append(i);
          break;
        case 43:
          localStringBuilder.append(i);
          break;
        case 33:
          localStringBuilder.append(i);
          break;
        case 42:
          localStringBuilder.append(i);
          break;
        case 39:
          localStringBuilder.append(i);
          break;
        case 40:
          localStringBuilder.append(i);
          break;
        case 41:
          localStringBuilder.append(i);
          break;
        case 44:
          localStringBuilder.append(i);
          break;
        case 123:
          localStringBuilder.append(i);
          break;
        case 125:
          localStringBuilder.append(i);
          break;
        case 124:
          localStringBuilder.append(i);
          break;
        case 92:
          localStringBuilder.append(i);
          break;
        case 94:
          localStringBuilder.append(i);
          break;
        case 126:
          localStringBuilder.append(i);
          break;
        case 91:
          localStringBuilder.append(i);
          break;
        case 93:
          localStringBuilder.append(i);
          break;
        case 96:
          localStringBuilder.append(i);
          break;
        case 34:
          localStringBuilder.append(i);
          break;
        case 60:
          localStringBuilder.append(i);
          break;
        case 62:
          localStringBuilder.append(i);
          break;
        case 35:
          localStringBuilder.append(i);
          break;
        case 37:
          localStringBuilder.append(i);
          break;
        case 59:
          localStringBuilder.append(i);
          break;
        case 47:
          localStringBuilder.append(i);
          break;
        case 63:
          localStringBuilder.append(i);
        case 38:
        case 48:
        case 49:
        case 50:
        case 51:
        case 52:
        case 53:
        case 54:
        case 55:
        case 56:
        case 57:
        case 58:
        case 61:
        case 64:
        case 65:
        case 66:
        case 67:
        case 68:
        case 69:
        case 70:
        case 71:
        case 72:
        case 73:
        case 74:
        case 75:
        case 76:
        case 77:
        case 78:
        case 79:
        case 80:
        case 81:
        case 82:
        case 83:
        case 84:
        case 85:
        case 86:
        case 87:
        case 88:
        case 89:
        case 90:
        case 97:
        case 98:
        case 99:
        case 100:
        case 101:
        case 102:
        case 103:
        case 104:
        case 105:
        case 106:
        case 107:
        case 108:
        case 109:
        case 110:
        case 111:
        case 112:
        case 113:
        case 114:
        case 115:
        case 116:
        case 117:
        case 118:
        case 119:
        case 120:
        case 121:
        case 122: }  }  return localStringBuilder.toString();
  }

  @Deprecated
  public static byte[] sha1(byte[] paramArrayOfByte)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("SHA-1");
      return localMessageDigest.digest(paramArrayOfByte);
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  @Deprecated
  public static String sha1Hex(byte[] paramArrayOfByte)
  {
    return toHex(sha1(paramArrayOfByte));
  }

  @Deprecated
  public static byte[] secureId()
  {
    try
    {
      if (lock.start())
      {
        try
        {
          prng = SecureRandom.getInstance("SHA1PRNG");
        } finally {
          lock.end();
        }
      }
      int i = 64;

      Long localLong = Long.valueOf(new Date().getTime());

      byte[] arrayOfByte = new byte[i - 8];
      prng.nextBytes(arrayOfByte);
      ByteBuffer localByteBuffer = ByteBuffer.allocate(i);
      localByteBuffer.putLong(localLong.longValue());
      localByteBuffer.put(arrayOfByte);

      return sha1(localByteBuffer.array());
    } catch (Exception localException) {
      localException.printStackTrace();
    }
    return null;
  }
}