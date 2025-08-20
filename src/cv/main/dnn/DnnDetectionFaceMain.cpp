//
// Created by HP on 2025/8/15.
//
#include <iostream>
#include "smartsecurity/cv/cvexport.h"
using  namespace cv_dnn::face;

extern "C"{
[[maybe_unused]] CORE_CV_API DnnDetectorFace* DnnDetectorFaceCreate(cv_dnn::param::face::FaceParam* param){
        std::cout<<"DnnDetectorFaceCreate"<<std::endl;
        return new DnnDetectorFace(param);
    }
[[maybe_unused]] CORE_CV_API void DnnDetectorFaceDestroy(DnnDetectorFace* detector){
        delete detector;
    }
}
extern "C"{
[[maybe_unused]] CORE_CV_API void DnnDetectorFaceDetectImage(DnnDetectorFace* detector,
                                    unsigned char *inputData, int size,
                                    data::ImageData &OutputData){
        detector->DetectImage(inputData,size,OutputData);
    }
[[maybe_unused]] CORE_CV_API void DnnDetectorFaceDetectImageTest(DnnDetectorFace* detector,data::ImageData &OutputData){
        detector->DetectImageTest(OutputData);
    }
[[maybe_unused]] CORE_CV_API void DnnDetectorFaceGetFaceFeatureTest(
        DnnDetectorFace* detector, data::ImageData &OutputData,
        DnnFeatureFace *faceFeature
        ){
       try{
           detector->getFaceFeatureTest(OutputData,faceFeature);
       }catch(std::exception& e){
           std::cout<<"DnnDetectorFaceGetFaceFeatureTest error"<<e.what()<<std::endl;
       }
    }
    EXPORT_USE CORE_CV_API void DnnDetectorFaceGetFaceFeature(
        DnnDetectorFace* detector, unsigned char *inputData, int inSize,
        data::ImageData &OutputData,
        DnnFeatureFace *faceFeature,
        unsigned char*& faceData,
        int& faceDataSize
        ){
       try{
           detector->getFaceFeature(inputData,inSize,OutputData,faceFeature,faceData,faceDataSize);
       }catch(std::exception& e){
           std::cout<<"DnnDetectorFaceGetFaceFeature error"<<e.what()<<std::endl;
       }
    }
    EXPORT_USE CORE_CV_API void  DnnDetectorFaceGetFaceFeature_0(
            DnnDetectorFace* detector,
            unsigned char* inputData, int inSize,
            cv_param::EncodeParam* encodeParam,
            DnnFeatureFace *faceFeature,
            data::ImageData &OutputData,data::FaceFeatureByte& byteData
            ){
        detector->_getFaceFeature(inputData,inSize,encodeParam,faceFeature,OutputData,byteData);
    }

    EXPORT_USE CORE_CV_API double DnnDetectorFaceGetDistance(
            data::FaceFeatureByte* input_1,
            data::FaceFeatureByte* input_2
            ){
        return DnnDetectorFace::getDistance(input_1,input_2);
    }
    EXPORT_USE CORE_CV_API double DnnDetectorFaceGetDistanceForByte(
            unsigned char* input_1,
            unsigned char* input_2){
        return DnnDetectorFace::getDistanceForByte(input_1,input_2);
    }
}
