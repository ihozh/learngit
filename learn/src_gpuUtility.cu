#include<jni.h>
#include"src_gpuUtility.h"
#include<stdio.h>

JINEXPORT jobjectArray JNICALL Java_src_gpuUtility_caSimNoRank(
		JNIEnv *env, jclass jc, jobjectArray ele, jabjectArray lr,jobjectArray rr) {
	printf("caSimNoRank");

}
JINEXPORT jobjectArray JNICALL Java_src_gpuUtility_caSimRank(
		JNIEnv *env, jclass jc, jobjectArray ele) {
	printf("caSimRank");
}
JINEXPORT jobjectArray JNICALL Java_src_gpuUtility_raSimProc(
		JNIEne *env, jclass jc,jobjectArray ele) {
	printf("raSimProc");
}
