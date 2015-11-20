#include"src_gpuUtility.h"
#include<jni.h>
#include<stdio.h>
//#include"caSimNoRank.h"

JNIEXPORT jobjectArray JNICALL Java_src_gpuUtility_caSimNoRank(
		JNIEnv * env, jclass jclazz,jobjectArray ele,
		jobjectArray lr,jobjectArray rr) {
	printf("caSimNoRank\n");
	int rowlen = (*env)->GetArrayLength(env, ele);//number of row
	jarray myarray = (*env)->GetObjectArrayElement(env, ele, 0);
	int collen =(*env)->GetArrayLength(env, myarray);//number of col
	jint elementM [rowlen][collen];
	jint i,j;
	for (i = 0;i<rowlen;i++) {
		myarray = (*env)->GetObjectArrayElement(env, ele, i);
		jint *coldata = (*env)->GetIntArrayElements(env, (jintArray)myarray, 0 );
		for (j=0;j<collen;j++) {
			elementM [i][j] = coldata[j];
			printf("%d",coldata[j]);
		}
		(*env)->ReleaseIntArrayElements(env, (jintArray)myarray, coldata,0 );
		printf("\n");
	}
	return ele;
}
JNIEXPORT jstring JNICALL Java_src_gpuUtility_getString (JNIEnv * env,
		jclass cls, jstring s) {
	const char *nativeString = (*env)->GetStringUTFChars(env, s, 0);
	return s;
}
JNIEXPORT jintArray JNICALL Java_src_gpuUtility_getArray (JNIEnv * env,
		jclass cls, jintArray args) {
	return args;
}
