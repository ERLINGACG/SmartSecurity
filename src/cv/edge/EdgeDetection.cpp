
#include "smartsecurity/cv/edge/edgedetection.h"
#include <iostream>

void cv_edge::EdgeDetection::SodelDetector(
    param::EdgeParam* param, 
    unsigned char*  inputImg,
    int             ImgSize,
    data::ImageData& outPutData
){
      auto start_total = std::chrono::system_clock::now(); // 总开始时间
    
      auto t1 = std::chrono::system_clock::now();
      std::vector<unsigned char> buf(inputImg, inputImg + ImgSize); //构造临时缓冲区
      cv::Mat input = cv::imdecode(buf, cv::IMREAD_GRAYSCALE);  //解码为灰度图
      cv::Mat grad_x, grad_y; //x方向梯度，y方向梯度
      auto t2 = std::chrono::system_clock::now();


      cv::GaussianBlur(input, input, cv::Size(param->gauss_size | 0x1, param->gauss_size | 0x1), 0, 0); //高斯滤波
      auto t3 = std::chrono::system_clock::now();
      
      cv::Sobel(input, grad_x, CV_16S, 1, 0, param->ksize, param->scale, param->delta, cv::BORDER_DEFAULT); //x方向梯度
      cv::Sobel(input, grad_y, CV_16S, 0, 1, param->ksize, param->scale, param->delta, cv::BORDER_DEFAULT); //y方向梯度
      cv::convertScaleAbs(grad_x, grad_x); //转换为绝对值
      cv::convertScaleAbs(grad_y, grad_y); //转换为绝对值
      auto t4 = std::chrono::system_clock::now();


      cv::Mat combined;
      cv::addWeighted(grad_x, 0.5, grad_y, 0.5, 0, combined); //梯度和
      std::vector<int> params_ {cv::IMWRITE_WEBP_QUALITY, param->imageqos};
      std::vector<unsigned char> outBuf;
      cv::imencode(param->imageCode, combined, outBuf, params_); 
      auto t5 = std::chrono::system_clock::now();

      outPutData.width = combined.cols;
      outPutData.height = combined.rows;
      outPutData.channels = combined.channels();
      outPutData.size = static_cast<int>(outBuf.size());
      outPutData.data = std::make_unique<unsigned char[]>(outPutData.width * outPutData.height * outPutData.channels);
      memcpy(outPutData.data.get(), outBuf.data(), outPutData.size);
      
       // 打印各阶段耗时（毫秒）
      auto fmt_time = [](auto time) {
        auto us = std::chrono::duration_cast<std::chrono::microseconds>(time).count();
        return std::to_string(us/1000.0) + "ms"; // 显示毫秒带小数
      };

    std::cout << std::fixed << std::setprecision(2); // 设置两位小数
    std::cout << "[Perf] 解码耗时: " << fmt_time(t2 - t1) 
            << " | 滤波耗时: " << fmt_time(t3 - t2)
            << " | Sobel耗时: " << fmt_time(t4 - t3)
            << " | 编码耗时: " << fmt_time(t5 - t4)
            << " | 总耗时: " << fmt_time(t5 - start_total) 
            << std::endl;
}
