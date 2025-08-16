#include "smartsecurity/core/export.h"
#include <chrono> 
#include <iostream>
float swap_endian(float value) {
    union {
        float f;
        uint8_t b[4];
    } src, dst;
    
    src.f = value;
    dst.b[0] = src.b[3];
    dst.b[1] = src.b[2];
    dst.b[2] = src.b[1];
    dst.b[3] = src.b[0];
    return dst.f;
}
extern "C" {
    CORE_API void SmartSecurityCoreLibTest() {
        std::cout << "SmartSecurityCoreLibTest" << std::endl;
    }
    CORE_API void SmartSecurityCoreLibTest_() {
        std::cout << "SmartSecurityCoreLibTest_2" << std::endl;
    }
    CORE_API void SmartSecurityCoreLibTest2(const char* path) {
        cv::Mat img = cv::imread(path);
        if(!img.empty()){
            std::cout<<"load success"<<std::endl;
            std::cout<<"size:"<<img.size()<<std::endl;
        }
    }
    CORE_API smartsecurity::structs::CoreStruct* SmartSecurityCoreLibTest3(const char* path) {
        cv::Mat img = cv::imread(path);
        auto coreStruct = std::make_unique<smartsecurity::structs::CoreStruct>();
        if(!img.empty()) {
            std::vector<unsigned char> buffer;
            cv::imencode(".jpg", img, buffer);
            
            coreStruct->width = img.cols;
            coreStruct->height = img.rows;
            coreStruct->size = buffer.size();
            coreStruct->data = std::make_unique<unsigned char[]>(coreStruct->size);
            memcpy(coreStruct->data.get(), buffer.data(), coreStruct->size);
            std::cout<<"load success"<<std::endl;
        }
        return coreStruct.release();
    }
    CORE_API void SmartSecurityCoreLibTest4(
        const char* path, smartsecurity::structs::CoreStruct& coreStruct) {
        cv::Mat img = cv::imread(path);
        if(!img.empty()) {
            std::vector<unsigned char> buffer;
            cv::imencode(".jpg", img, buffer);
            coreStruct.width = img.cols;
            coreStruct.height = img.rows;
            coreStruct.size = buffer.size();
            coreStruct.data = std::make_unique<unsigned char[]>(coreStruct.size);
            memcpy(coreStruct.data.get(), buffer.data(), coreStruct.size);
            std::cout<<"load success"<<std::endl;
        }
    }
    CORE_API void SmartSecurityCoreLibTest_dlib(
        const char* path,
        const char* modelpath, 
        smartsecurity::structs::CoreStruct& coreStruct) {

        auto start1 = std::chrono::high_resolution_clock::now(); // 记录起始时间
        frontal_face_detector detector = get_frontal_face_detector();
        shape_predictor sp;
        deserialize(modelpath) >> sp;
        auto end1 = std::chrono::high_resolution_clock::now(); // 记录结束时间
        std::chrono::duration<double, std::milli> duration1 = end1 - start1; // 计算耗时
        std::cout << "load model time: " << duration1.count() << " ms" << std::endl;

        
        cv::Mat img = cv::imread(path);
        if(!img.empty()) {
            // dlib::pyramid_up(img);
            auto start2 = std::chrono::high_resolution_clock::now(); // 记录起始时间
            dlib::cv_image<dlib::bgr_pixel> cimg(img);
            std::vector<dlib::rectangle> faces = detector(cimg,1); // 金字塔层数
            auto end2 = std::chrono::high_resolution_clock::now(); // 记录结束时间
            std::chrono::duration<double, std::milli> duration2 = end2 - start2; // 计算耗时
            std::cout << "detect face time: " << duration2.count() << " ms" << std::endl;
            
            for (const auto& face : faces) {
                cv::rectangle(img,
                    cv::Point(face.left(), face.top()),
                    cv::Point(face.right(), face.bottom()),
                    cv::Scalar(0, 255, 0),  // 绿色边框
                    2);                     // 线宽

                auto shape = sp(cimg, face);
                 // 绘制特征点连线（新增以下代码）
                // 定义需要连线的特征点索引对
                const std::vector<std::pair<int,int>> connections = {
                    {0,16},    // 下巴轮廓
                    {17,21},   // 右眉
                    {22,26},   // 左眉
                    {27,30},   // 鼻梁
                    {30,35},   // 鼻翼
                    {36,41},   // 右眼
                    {42,47},   // 左眼
                    {48,59},   // 外嘴唇
                    {60,67}    // 内嘴唇
                };
                // 绘制连线
                for (const auto& conn : connections) {
                    for(int i=conn.first; i<conn.second; ++i){
                        cv::line(img,
                            cv::Point(shape.part(i).x(), shape.part(i).y()),
                            cv::Point(shape.part(i+1).x(), shape.part(i+1).y()),
                            cv::Scalar(255, 0, 0), // 蓝色连线
                            1);                    // 线宽
                    }
                }
            }

            std::vector<unsigned char> buffer;
            cv::imencode(".jpg", img, buffer);

                coreStruct.width = img.cols;
                coreStruct.height = img.rows;
                coreStruct.size = buffer.size();
                coreStruct.data = std::make_unique<unsigned char[]>(coreStruct.size);
                memcpy(coreStruct.data.get(), buffer.data(), coreStruct.size);
                
            std::cout<<"load success"<<std::endl;
        }
    }
    CORE_API void SmartSecurityCoreLibTest_dlib2(
        unsigned char* inputData,
        int dataSize,  
        const char* modelpath, 
        smartsecurity::structs::CoreStruct& coreStruct){

        auto start1 = std::chrono::high_resolution_clock::now(); // 记录起始时间
        frontal_face_detector detector = get_frontal_face_detector();
        shape_predictor sp;
        deserialize(modelpath) >> sp;
        auto end1 = std::chrono::high_resolution_clock::now(); // 记录结束时间
        std::chrono::duration<double, std::milli> duration1 = end1 - start1; // 计算耗时
        std::cout << "load model time: " << duration1.count() << " ms" << std::endl;
       
        std::vector<unsigned char> jpegBuffer(inputData, inputData + dataSize);
        // 解码JPEG
        cv::Mat img = cv::imdecode(jpegBuffer, cv::IMREAD_COLOR);
        if(!img.empty()){

             auto start2 = std::chrono::high_resolution_clock::now(); // 记录起始时间
            dlib::cv_image<dlib::bgr_pixel> cimg(img);
            std::vector<dlib::rectangle> faces = detector(cimg,1); // 金字塔层数
            auto end2 = std::chrono::high_resolution_clock::now(); // 记录结束时间
            std::chrono::duration<double, std::milli> duration2 = end2 - start2; // 计算耗时
            std::cout << "detect face time: " << duration2.count() << " ms" << std::endl;
            
            for (const auto& face : faces) {
                cv::rectangle(img,
                    cv::Point(face.left(), face.top()),
                    cv::Point(face.right(), face.bottom()),
                    cv::Scalar(0, 255, 0),  // 绿色边框
                    2);                     // 线宽

                auto shape = sp(cimg, face);
                 // 绘制特征点连线（新增以下代码）
                // 定义需要连线的特征点索引对
                const std::vector<std::pair<int,int>> connections = {
                    {0,16},    // 下巴轮廓
                    {17,21},   // 右眉
                    {22,26},   // 左眉
                    {27,30},   // 鼻梁
                    {30,35},   // 鼻翼
                    {36,41},   // 右眼
                    {42,47},   // 左眼
                    {48,59},   // 外嘴唇
                    {60,67}    // 内嘴唇
                };
                // 绘制连线
                for (const auto& conn : connections) {
                    for(int i=conn.first; i<conn.second; ++i){
                        cv::line(img,
                            cv::Point(shape.part(i).x(), shape.part(i).y()),
                            cv::Point(shape.part(i+1).x(), shape.part(i+1).y()),
                            cv::Scalar(255, 0, 0), // 蓝色连线
                            1);                    // 线宽
                    }
                }
            }
            std::vector<unsigned char> buffer;
            cv::imencode(".jpg", img, buffer);

                coreStruct.width = img.cols;
                coreStruct.height = img.rows;
                coreStruct.size = buffer.size();
                coreStruct.data = std::make_unique<unsigned char[]>(coreStruct.size);
                memcpy(coreStruct.data.get(), buffer.data(), coreStruct.size);
                
            std::cout<<"load success"<<std::endl;
        }

    }
    CORE_API void SmartSecurityCoreLibTest_dlib3(
        unsigned char* inputData_1,
        int inputDataSize_1,
        unsigned char*  inputData_2,
        int inputDataSize_2,
        const char* predictor_path, 
        const char* recognition_Path
    ){
        // dlib::cuda::set_device(0); 
        auto start1 = std::chrono::high_resolution_clock::now(); // 记录起始时间
        anet_type net;
        shape_predictor sp;
        deserialize(predictor_path) >> sp;      // 加载特征点模型
        deserialize(recognition_Path) >> net;  // 加载识别模型
        auto end1 = std::chrono::high_resolution_clock::now(); // 记录结束时间
        std::chrono::duration<double, std::milli> duration1 = end1 - start1; // 计算耗时
        std::cout << "load model time: " << duration1.count() << " ms" << std::endl;
      //解码图像
        auto start2 = std::chrono::high_resolution_clock::now(); // 记录起始时间
        cv::Mat img1 = cv::imdecode(cv::Mat(1, inputDataSize_1, CV_8U, inputData_1), cv::IMREAD_COLOR);
        cv::Mat img2 = cv::imdecode(cv::Mat(1, inputDataSize_2, CV_8U, inputData_2), cv::IMREAD_COLOR);
        auto end2 = std::chrono::high_resolution_clock::now(); // 记录结束时间
        std::chrono::duration<double, std::milli> duration2 = end2 - start2; // 计算耗时
        std::cout << "decode image time: " << duration2.count() << " ms" << std::endl;
        
        if(!img1.empty() && !img2.empty()){
             auto start3 = std::chrono::high_resolution_clock::now(); // 记录起始时间
             frontal_face_detector detector = get_frontal_face_detector();
    
            // 处理第一张图片
            dlib::matrix<dlib::rgb_pixel> face1;
            {
                dlib::cv_image<dlib::bgr_pixel> cimg(img1);
                auto faces = detector(cimg);
                if(faces.empty()) return;
                
                auto shape = sp(cimg, faces[0]);
                dlib::extract_image_chip(cimg, dlib::get_face_chip_details(shape,150,0.25), face1);
            }
            // 处理第二张图片
            dlib::matrix<dlib::rgb_pixel> face2;
            {
                dlib::cv_image<dlib::bgr_pixel> cimg(img2);
                auto faces = detector(cimg);
                if(faces.empty()) return;
                
                auto shape = sp(cimg, faces[0]);
                dlib::extract_image_chip(cimg, dlib::get_face_chip_details(shape,150,0.25), face2);
            }
            auto feature1 = net(face1);
            auto feature2 = net(face2);
            double distance = dlib::length(feature1 - feature2);
            std::cout << "Face distance: " << distance << std::endl;
            if(distance < 0.6) {
                std::cout << "The two faces are the same person." << std::endl;
            }else{
                std::cout << "The two faces are different persons." << std::endl;
            }
            auto end3 = std::chrono::high_resolution_clock::now(); // 记录结束时间
            std::chrono::duration<double, std::milli> duration3 = end3 - start3; // 计算耗时
            std::cout << "face recognition time: " << duration3.count() << " ms" << std::endl;
            // 人脸识别
        }
    }
    CORE_API void SmartSecurityCoreLibTest_dlib4(
         unsigned char* inputData_1,
         int inputDataSize_1,
         const char* predictor_path, 
         const char* recognition_Path,
        unsigned char* result_buffer 
    ){
        anet_type net;
        shape_predictor sp;
        deserialize(predictor_path) >> sp;      // 加载特征点模型
        deserialize(recognition_Path) >> net;  // 加载识别模型
        cv::Mat img1 = cv::imdecode(cv::Mat(1, inputDataSize_1, CV_8U, inputData_1), cv::IMREAD_COLOR);
        if(!img1.empty()){
            frontal_face_detector detector = get_frontal_face_detector();
            dlib::matrix<dlib::rgb_pixel> face1;
            {
                dlib::cv_image<dlib::bgr_pixel> cimg(img1);
                auto faces = detector(cimg); //检测人脸区域
                if(faces.empty()) return;     
                
                auto shape = sp(cimg, faces[0]); //提取人脸特征点
                dlib::extract_image_chip(cimg, dlib::get_face_chip_details(shape,150,0.25), face1); //提取人脸图像
            }
            auto feature1 = net(face1); //计算人脸特征值
            for(int i=0;i<128;i++){
            
                std::cout<<feature1(i)<<std::endl;
            }
            // 直接拷贝内存到字节数组（保持C++原生字节序）
            memcpy(result_buffer, &feature1(0), 128*sizeof(float));
        }
    }
    CORE_API void SmartSecurityCoreLibTest_dlib5(unsigned char* byteData,int dataSize){
            int numFloats = dataSize / sizeof(float);
            float* floatArray = reinterpret_cast<float*>(byteData);
            
            for(int i = 0; i < numFloats; ++i) {
                std::cout << "Float[" << i << "] = " << floatArray[i] << std::endl;
            }
    }
    CORE_API void SmartSecurityCoreLibTest_dlib6(
        unsigned char* byteData1,
        int dataSize1,
        unsigned char* byteData2,
        int dataSize2
    ){
        int numFloats1 = dataSize1 / sizeof(float);
        int numFloats2 = dataSize2 / sizeof(float);
        float* floatArray1 = reinterpret_cast<float*>(byteData1);
        float* floatArray2 = reinterpret_cast<float*>(byteData2);

        float sum=0.0f;
        for(int i=0; i<128; ++i) {
            float diff =floatArray1[i] - floatArray2[i];
            sum += diff*diff;
        }
        std::cout << "Euclidean distance: " << std::sqrt(sum) << std::endl;
    }
}