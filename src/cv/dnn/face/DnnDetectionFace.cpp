//
// Created by HP on 2025/8/15.
//
#include <iostream>

#include "smartsecurity/cv/dnn/dnnDetectionFace.h"
#include "smartsecurity/cv/utils/dnnUtils.h"
#include "opencv2/opencv.hpp"

using namespace  cv_dnn::face;

DnnDetectorFace::DnnDetectorFace(cv_dnn::param::face::FaceParam* param){
    this->InitModelFace(param);
}

void DnnDetectorFace::DetectImage(unsigned char *inputData, int size, data::ImageData &OutputData) {
  //  pass;
}

void DnnDetectorFace::DetectImageTest(data::ImageData& OutputData) {
    cv::Mat image = cv::imread(R"(E:\SmartSecurity\SmartSecurityWeb\lib\example\test3.png)");
    if(image.empty()){
        std::cout<<"image empty"<<std::endl;
        return;
    }else{
        std::cout<<"image not empty,rows:"<<image.rows<<",cols:"<<image.cols<<std::endl;
    }
    cv::resize(image,image,cv::Size(300,300));

    cv::Mat blob= cv::dnn::blobFromImage(image,1.0,
                                         cv::Size(300,300),
                                         cv::Scalar(104,117,123),false,false);
    std::cout<<"blob size:"<<blob.size<<std::endl;
    // 设置网络输入
    try{
        std::cout<<"try to setInput"<<blob.size<<std::endl;
        this->net.setInput(blob);
        std::cout<<"net input success"<<std::endl;
    }catch (std::exception& e){
        std::cerr << "OpenCV error: " << e.what() << std::endl;
    }
    try{
        // 前向传播
        cv::Mat output = this->net.forward();
        std::cout<<"net forward success"<<std::endl;

        // 添加人脸检测结果处理
        cv::Mat detections(output.size[2], output.size[3], CV_32F, output.ptr<float>());
        const float confidence_threshold = 0.5;

        std::vector<cv::Rect> faces;
        for(int i = 0; i < detections.rows; ++i) {
            float confidence = detections.at<float>(i, 2);

            if(confidence > confidence_threshold) {
                int x1 = static_cast<int>(detections.at<float>(i, 3) *  static_cast<float>(image.cols));
                int y1 = static_cast<int>(detections.at<float>(i, 4) *  static_cast<float>(image.rows));
                int x2 = static_cast<int>(detections.at<float>(i, 5) *  static_cast<float>(image.cols));
                int y2 = static_cast<int>(detections.at<float>(i, 6) *  static_cast<float>(image.rows));

                faces.emplace_back(x1, y1, x2 - x1, y2 - y1);

                // 绘制检测结果（可选）
                cv::rectangle(image, cv::Point(x1, y1), cv::Point(x2, y2), cv::Scalar(0, 255, 0), 2);
            }
        }

        std::cout << "Detected " << faces.size() << " faces" << std::endl;
        std::vector<int> params_ {cv::IMWRITE_WEBP_QUALITY, 95};
        std::vector<unsigned char> encoded;
        cv::imencode(".jpeg", image, encoded, params_);

        std::cout<<"编码完成"<<std::endl;
        OutputData.size =  static_cast<int>(encoded.size());
        OutputData.width = image.cols;
        OutputData.height = image.rows;
        OutputData.channels = 3;
        OutputData.data = std::make_unique<unsigned char[]>(encoded.size());
        memcpy(OutputData.data.get(), encoded.data(), encoded.size());
    }catch (std::exception& e){
        std::cerr << "OpenCV error: " << e.what() << std::endl;
    }

}

void DnnDetectorFace::LoadModelFace(cv_dnn::param::face::FaceParam *param) {
    std::cout << "caffemodel_path:" << param->caffemodel_path << std::endl;
    std::cout << "prototxt_path:" << param->prototxt_path << std::endl;
    std::cout<<"isCuda:"<<param->isCuda<<std::endl;
    try{
        this->net=cv::dnn::readNetFromCaffe(param->prototxt_path,param->caffemodel_path);
        if (this->net.empty()) {
            std::cerr << "Failed to load model!" << std::endl;
        }else{
            std::cout<<"net load success"<<std::endl;
        }
        if(param->isCuda){
            this->net.setPreferableBackend(cv::dnn::DNN_BACKEND_CUDA);
            this->net.setPreferableTarget(cv::dnn::DNN_TARGET_CUDA);
            std::cout<<"DNN_BACKEND_CUDA"<<std::endl;
        }
    }catch (const cv::Exception& e){
        std::cerr << "OpenCV error: " << e.what() << std::endl;
        return;
    }

}

void DnnDetectorFace::InitModelFace(cv_dnn::param::face::FaceParam *param) {
    this->LoadModelFace(param);
}

void DnnDetectorFace::getFaceFeatureTest(data::ImageData &OutputData, cv_dnn::face::DnnFeatureFace* featureExtractor) {
    cv::Mat image = cv::imread(R"(E:\SmartSecurity\SmartSecurityWeb\lib\example\test3.png)");
    if(image.empty()){
        std::cout<<"image empty"<<std::endl;
        return;
    }else{
        std::cout<<"image not empty,rows:"<<image.rows<<",cols:"<<image.cols<<std::endl;
    }
    cv::resize(image,image,cv::Size(300,300));
    cv::Mat originalImage = image.clone();
    cv::Mat blob= cv::dnn::blobFromImage(image,1.0,
                                         cv::Size(300,300),
                                         cv::Scalar(104,117,123),false,false);
    std::cout<<"blob size:"<<blob.size<<std::endl;
    // 设置网络输入
    try{
        std::cout<<"try to setInput"<<blob.size<<std::endl;
        this->net.setInput(blob);
        std::cout<<"net input success"<<std::endl;
    }catch (std::exception& e){
        std::cerr << "OpenCV error: " << e.what() << std::endl;
    }
    try{
        // 前向传播
        cv::Mat output = this->net.forward();
        std::cout<<"net forward success"<<std::endl;

        // 添加人脸检测结果处理
        cv::Mat detections(output.size[2], output.size[3], CV_32F, output.ptr<float>());
        const float confidence_threshold = 0.5;

        std::vector<cv::Rect> faces;
        std::vector<float> confidences;
        for(int i = 0; i < detections.rows; ++i) {
            float confidence = detections.at<float>(i, 2);

            if(confidence > confidence_threshold) {
                int x1 = static_cast<int>(detections.at<float>(i, 3) *  static_cast<float>(image.cols));
                int y1 = static_cast<int>(detections.at<float>(i, 4) *  static_cast<float>(image.rows));
                int x2 = static_cast<int>(detections.at<float>(i, 5) *  static_cast<float>(image.cols));
                int y2 = static_cast<int>(detections.at<float>(i, 6) *  static_cast<float>(image.rows));

                faces.emplace_back(x1, y1, x2 - x1, y2 - y1);
                confidences.emplace_back(confidence);
                // 绘制检测结果（可选）
                cv::rectangle(originalImage, cv::Point(x1, y1), cv::Point(x2, y2), cv::Scalar(0, 255, 0), 2);
            }
        }
        // 存储所有检测到的人脸特征向量
        std::vector<std::vector<float>> faceFeatures;
        for(size_t i = 0; i < faces.size(); ++i){
            const auto& faceRect = faces[i];
            // 裁剪人脸区域
            cv::Mat faceROI = image(faceRect).clone();
            // 预处理人脸图像（根据特征提取模型的要求）
            cv::Mat inputBlob = cv::dnn::blobFromImage(
                    faceROI,
                    1.0 / 128,              // 缩放因子
                    cv::Size(160, 160),      // 特征提取模型输入尺寸
                    cv::Scalar(127.5, 127.5, 127.5), // 均值
                    true,                    // 交换R和B通道
                    false                    // 不裁剪
            );
            // 设置输入并前向传播
            featureExtractor->getNet().setInput(inputBlob);
            cv::Mat featureVector = featureExtractor->getNet().forward();

            // 将特征向量转换为std::vector<float>
            std::vector<float> featureVec(
                    featureVector.ptr<float>(),
                    featureVector.ptr<float>() + featureVector.total()
            );

            faceFeatures.push_back(featureVec);

            // 在图像上显示特征信息
            std::string label = cv::format("Face %zu (%.2f)", i, confidences[i]);
            cv::putText(originalImage, label, cv::Point(faceRect.x, faceRect.y - 5),
                        cv::FONT_HERSHEY_SIMPLEX, 0.5, cv::Scalar(0, 255, 0), 1);

            std::cout << "Extracted feature vector for face " << i
                      << " (size: " << featureVec.size() << ")" << std::endl;
            for(auto& feature:featureVec){
                std::cout<<feature<<std::endl;
            }

        }

        std::cout << "Detected " << faces.size() << " faces" << std::endl;
        std::cout << "Extracted " << faceFeatures.size() << " face features" << std::endl;
        std::vector<int> params_ {cv::IMWRITE_WEBP_QUALITY, 95};
        std::vector<unsigned char> encoded;
        cv::imencode(".jpeg", originalImage, encoded, params_);

        std::cout<<"编码完成"<<std::endl;
        OutputData.size =  static_cast<int>(encoded.size());
        OutputData.width = image.cols;
        OutputData.height = image.rows;
        OutputData.channels = 3;
        OutputData.data = std::make_unique<unsigned char[]>(encoded.size());
        memcpy(OutputData.data.get(), encoded.data(), encoded.size());
    }catch (std::exception& e){
        std::cerr << "OpenCV error: " << e.what() << std::endl;
    }
}


void DnnDetectorFace::getFaceFeature(unsigned char* inputData, int inSize,
                                     data::ImageData &OutputData,
                                     cv_dnn::face::DnnFeatureFace* featureExtractor,
                                     unsigned char *&faceData,
                                     int& faceDataSize
) {

    cv::Mat image = cv::imdecode(cv::Mat(1, inSize, CV_8U, inputData), cv::IMREAD_COLOR);
    if(image.empty()){
        std::cout<<"image empty"<<std::endl;
        return;
    }else{
        std::cout<<"image not empty,rows:"<<image.rows<<",cols:"<<image.cols<<std::endl;
    }
    cv::resize(image,image,cv::Size(300,300));
    cv::Mat originalImage = image.clone();
//    cv::Mat blob= cv::dnn::blobFromImage(image,1.0,
//                                         cv::Size(300,300),
//                                         cv::Scalar(104,117,123),false,false);
    cv::Mat blob;
    this->SetBlob(blob,originalImage);
    std::cout<<"blob size:"<<blob.size<<std::endl;
    // 设置网络输入
    try{
        std::cout<<"try to setInput"<<blob.size<<std::endl;
        this->net.setInput(blob);
        std::cout<<"net input success"<<std::endl;
    }catch (std::exception& e){
        std::cerr << "OpenCV error: " << e.what() << std::endl;
    }
    try{
        // 前向传播
        cv::Mat output = this->net.forward();
        std::cout<<"net forward success"<<std::endl;

        // 添加人脸检测结果处理
        cv::Mat detections(output.size[2], output.size[3], CV_32F, output.ptr<float>());
        const float confidence_threshold = 0.5;

        std::vector<cv::Rect> faces;
        std::vector<float> confidences;
        for(int i = 0; i < detections.rows; ++i) {
            float confidence = detections.at<float>(i, 2);

            if(confidence > confidence_threshold) {
                int x1 = static_cast<int>(detections.at<float>(i, 3) *  static_cast<float>(image.cols));
                int y1 = static_cast<int>(detections.at<float>(i, 4) *  static_cast<float>(image.rows));
                int x2 = static_cast<int>(detections.at<float>(i, 5) *  static_cast<float>(image.cols));
                int y2 = static_cast<int>(detections.at<float>(i, 6) *  static_cast<float>(image.rows));

                faces.emplace_back(x1, y1, x2 - x1, y2 - y1);
                confidences.emplace_back(confidence);
                // 绘制检测结果（可选）
                cv::rectangle(originalImage, cv::Point(x1, y1), cv::Point(x2, y2), cv::Scalar(0, 255, 0), 2);
            }
        }
        // 存储所有检测到的人脸特征向量
        std::vector<std::vector<float>> faceFeatures;

        for(size_t i = 0; i < faces.size(); ++i){
            const auto& faceRect = faces[i];
            // 裁剪人脸区域
            cv::Mat faceROI = image(faceRect).clone();
            // 预处理人脸图像（根据特征提取模型的要求）
            cv::Mat inputBlob = cv::dnn::blobFromImage(
                    faceROI,
                    1.0 / 128,              // 缩放因子
                    cv::Size(160, 160),      // 特征提取模型输入尺寸
                    cv::Scalar(127.5, 127.5, 127.5), // 均值
                    true,                    // 交换R和B通道
                    false                    // 不裁剪
            );
            // 设置输入并前向传播
            featureExtractor->getNet().setInput(inputBlob);
            cv::Mat featureVector = featureExtractor->getNet().forward();

            // 将特征向量转换为std::vector<float>
            std::vector<float> featureVec(
                    featureVector.ptr<float>(),
                    featureVector.ptr<float>() + featureVector.total()
            );

            faceFeatures.push_back(featureVec);

            // 在图像上显示特征信息
            std::string label = cv::format("Face %zu (%.2f)", i, confidences[i]);
            cv::putText(originalImage, label, cv::Point(faceRect.x, faceRect.y - 5),
                        cv::FONT_HERSHEY_SIMPLEX, 0.5, cv::Scalar(0, 255, 0), 1);

            std::cout << "Extracted feature vector for face " << i
                      << " (size: " << featureVec.size() << ")" << std::endl;
            size_t byteSize = featureVec.size() * sizeof(float);
            unsigned char* byteArray = new unsigned char[byteSize];
            memcpy(byteArray, featureVec.data(), byteSize);

            faceDataSize = static_cast<int>(byteSize);
            faceData = byteArray;
        }

        std::cout << "Detected " << faces.size() << " faces" << std::endl;
        std::cout << "Extracted " << faceFeatures.size() << " face features" << std::endl;
        std::vector<int> params_ {cv::IMWRITE_WEBP_QUALITY, 95};
        std::vector<unsigned char> encoded;
        cv::imencode(".jpeg", originalImage, encoded, params_);

        std::cout<<"编码完成"<<std::endl;
        OutputData.size =  static_cast<int>(encoded.size());
        OutputData.width = image.cols;
        OutputData.height = image.rows;
        OutputData.channels = 3;
        OutputData.data = std::make_unique<unsigned char[]>(encoded.size());
        memcpy(OutputData.data.get(), encoded.data(), encoded.size());
    }catch (std::exception& e){
        std::cerr << "OpenCV error: " << e.what() << std::endl;
    }
}
void DnnDetectorFace::_getFaceFeature(unsigned char* inputData, int size,
                                       cv_param::EncodeParam* encodeParam,
                                       cv_dnn::face::DnnFeatureFace* faceFeature,
                                       data::ImageData& OutputData,data::FaceFeatureByte& faceFeatureByte) {
    if (inputData == nullptr) {
        std::cout << "inputData is nullptr" << std::endl;
        return;
    }
    cv::Mat image;
    cv::Mat blob;
    cv::Mat output;
    std::vector<cv::Rect> faces;
    this->inputImage(inputData,size,image);
    if (image.empty()) {
        std::cerr << "Input image is empty after decoding" << std::endl;
        return;
    }
    this->resizeWithPadding(image,300,300);
    this->SetBlob(blob,image);
    this->Forward(output);
    cv_dnn::face::DnnDetectorFace::ProcessResults(output,image,0.6,faces);
    cv_dnn::face::DnnDetectorFace::ProcessResults_getFeature(faceFeature,faces,image,faceFeatureByte);
    this->outputImage(OutputData,image,encodeParam);

}


void DnnDetectorFace::SetBlob(cv::Mat& blob,cv::Mat& orgImage) {
    try {
        blob = cv::dnn::blobFromImage(orgImage, 1.0,
                                      cv::Size(300, 300),
                                      cv::Scalar(104, 117, 123), false, false);
        this->net.setInput(blob);
    } catch (std::exception &e) {
        std::cerr << "OpenCV error: " << e.what() << std::endl;
    }
}

void DnnDetectorFace::Forward(cv::Mat& output) {
    try{
        output=this->net.forward();
    }catch (std::exception& e){
        std::cerr << "OpenCV error: " << e.what() << std::endl;
    }
}
void DnnDetectorFace::ProcessResults(cv::Mat &output, cv::Mat& orgImage,double confidence_threshold,std::vector<cv::Rect>& _faces) {
    cv::Mat detections(output.size[2], output.size[3], CV_32F, output.ptr<float>());
    if(detections.empty()){
        std::cout << "No faces detected" << std::endl;
        return;
    }
    std::vector<cv::Rect> faces;

    std::vector<float> confidences;
    for(int i=0;i<detections.rows;i++){
        float confidence=detections.at<float>(i,2);
        if(confidence > confidence_threshold) {
            int x1 = static_cast<int>(detections.at<float>(i, 3) *  static_cast<float>(orgImage.cols));
            int y1 = static_cast<int>(detections.at<float>(i, 4) *  static_cast<float>(orgImage.rows));
            int x2 = static_cast<int>(detections.at<float>(i, 5) *  static_cast<float>(orgImage.cols));
            int y2 = static_cast<int>(detections.at<float>(i, 6) *  static_cast<float>(orgImage.rows));

            faces.emplace_back(x1, y1, x2 - x1, y2 - y1);
            confidences.emplace_back(confidence);
            // 绘制检测结果（可选）
            cv::rectangle(orgImage, cv::Point(x1, y1), cv::Point(x2, y2), cv::Scalar(0, 255, 0), 2);
        }
    }
    _faces=faces; //应该移到这里处理，否则_faces为空
}

void DnnDetectorFace::ProcessResults_getFeature(
        cv_dnn::face::DnnFeatureFace* featureExtractor,
        std::vector<cv::Rect>& faces,
        cv::Mat& orgImage,data::FaceFeatureByte& faceFeatureByte) {
    std::vector<std::vector<float>> faceFeatures;
    if(faces.empty()){
        std::cout << "No faces detected, faceFeatures size: " << faceFeatures.size() << std::endl;
        faceFeatureByte.dataSize=0;
        faceFeatureByte.faceNum=0;
        faceFeatureByte.data= std::make_unique<unsigned char[]>(0);
        return;
    }
    int faceNum=0;
    for(size_t i = 0; i < faces.size(); ++i) {
        const auto &faceRect = faces[i];
        // 裁剪人脸区域
        cv::Mat faceROI = orgImage(faceRect).clone();
        // 预处理人脸图像（根据特征提取模型的要求）
        cv::Mat inputBlob = cv::dnn::blobFromImage(
                faceROI,
                1.0 / 128,              // 缩放因子
                cv::Size(160, 160),      // 特征提取模型输入尺寸
                cv::Scalar(127.5, 127.5, 127.5), // 均值
                true,                    // 交换R和B通道
                false                    // 不裁剪
        );
        // 设置输入并前向传播
        featureExtractor->getNet().setInput(inputBlob);
        cv::Mat featureVector = featureExtractor->getNet().forward();

        // 将特征向量转换为std::vector<float>
        std::vector<float> featureVec(
                featureVector.ptr<float>(),
                featureVector.ptr<float>() + featureVector.total()
        );

        faceFeatures.push_back(featureVec);
        std::cout << "Extracted feature vector for face " << i
                  << " (size: " << featureVec.size() << ")" << std::endl;
        faceNum++;
    }
    size_t totalBytes = 0;
    std::vector<size_t> featureSizes;
    for(auto& vec : faceFeatures) {
        size_t singleSize = vec.size() * sizeof(float);
        featureSizes.push_back(singleSize);
        totalBytes += singleSize;
    }
    // 创建连续内存空间
    faceFeatureByte.data = std::make_unique<unsigned char[]>(totalBytes);
    faceFeatureByte.dataSize = totalBytes;
    faceFeatureByte.faceNum=faceNum;
    // 分段拷贝特征数据
    size_t offset = 0;
    for(size_t i = 0; i < faceFeatures.size(); ++i) {
        size_t copySize = featureSizes[i];
        memcpy(faceFeatureByte.data.get() + offset,
               faceFeatures[i].data(),
               copySize);
        offset += copySize;
    }

}

void DnnDetectorFace::inputImage(unsigned char *inputData, int size, cv::Mat &orgImage) {
    try{
        orgImage=cv::imdecode(cv::Mat(1,size,CV_8UC1,inputData),cv::IMREAD_COLOR);
        // 新增检查：如果图像为空，给出明确错误信息
        if (orgImage.empty()) {
            std::cerr << "Failed to decode image. Input data size: " << size << std::endl;
        }
    }catch (std::exception& e){
        std::cerr << "OpenCV error: for inputImage " << e.what() << std::endl;
    }
}

void DnnDetectorFace::outputImage(data::ImageData &OutputData,cv::Mat &orgImage, cv_param::EncodeParam *encodeParam) {
    try{
        if (orgImage.empty()) {
            std::cerr << "Cannot encode empty image" << std::endl;
            return;
        }
        std::vector<int> params_ {cv::IMWRITE_WEBP_QUALITY, encodeParam->qos};
        std::vector<unsigned char> encoded;
        cv::imencode(encodeParam->type, orgImage, encoded, params_);
        OutputData.size =  static_cast<int>(encoded.size());
        OutputData.width = orgImage.cols;
        OutputData.height = orgImage.rows;
        OutputData.channels = 3;
        OutputData.data = std::make_unique<unsigned char[]>(encoded.size());
        memcpy(OutputData.data.get(), encoded.data(), encoded.size());
    }catch (std::exception& e){
        std::cerr << "OpenCV error: for outputImage " << e.what() << std::endl;
    }
}
void DnnDetectorFace::resizeWithPadding(cv::Mat &orgImage,int inputWidth,int inputHeight) {
    try{
        if (orgImage.empty()) {
            std::cerr << "Cannot resize empty image" << std::endl;
            return;
        }
        double  scale =std::min(static_cast<double>(inputWidth)/ orgImage.cols,
                                static_cast<double>(inputHeight)/ orgImage.rows);

        cv::Size scaledSize(static_cast<int>(orgImage.cols*scale),
                            static_cast<int>(orgImage.rows*scale));
        //确定缩放因子
        cv::resize(orgImage,orgImage,scaledSize,0, 0, cv::INTER_LINEAR); //缩放图像

        int padH = inputHeight - orgImage.rows;
        int padW = inputWidth - orgImage.cols;
        // 填充图像
        cv::copyMakeBorder(orgImage, orgImage,
                        padH/2, padH - padH/2,  // 垂直方向（上下）
                        padW/2, padW - padW/2,  // 水平方向（左右）
                        cv::BORDER_CONSTANT, cv::Scalar(0,0,0));

    }catch (std::exception& e){
        std::cerr << "OpenCV error: for resizeWithPadding " << e.what() << std::endl;
    }
}

double DnnDetectorFace::getDistance(data::FaceFeatureByte* input_1, data::FaceFeatureByte* input_2) {
   cout<<"input_1->dataSize:"<<input_1->dataSize<<endl;
   cout<<"input_2->dataSize:"<<input_2->dataSize<<endl;

    auto* feat1 = reinterpret_cast<float*>(input_1->data.get());
    auto* feat2 = reinterpret_cast<float*>(input_2->data.get());

    // 获取特征向量维度
    int dim = input_1->dataSize / (input_1->faceNum * sizeof(float));

    // 创建OpenCV矩阵（假设单个人脸）
    cv::Mat vec1(1, dim, CV_32F, feat1);
    cv::Mat vec2(1, dim, CV_32F, feat2);

    double euclidean_dist = cv::norm(vec1 - vec2);

    return euclidean_dist; // 或返回 1 - euclidean_dist
}


double DnnDetectorFace::getDistanceForByte(unsigned char* input_1,
                                        unsigned char* input_2) {

    auto* feat1 = reinterpret_cast<float*>(input_1);
    auto* feat2 = reinterpret_cast<float*>(input_2);

    cv::Mat vec1(1, 128, CV_32F, feat1);
    cv::Mat vec2(1, 128, CV_32F, feat2);
    double euclidean_dist = cv::norm(vec1 - vec2);
    return euclidean_dist; // 或返回 1 - euclidean_dist
}

