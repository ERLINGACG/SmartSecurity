//
// Created by HP on 2025/8/18.
//

#ifndef SMARTSECURITYCORELIB_DNN_OUTPUT_TEMP_H
#define SMARTSECURITYCORELIB_DNN_OUTPUT_TEMP_H
#include <iostream>
#include <memory>

namespace cv_dnn::out{
    template<class T>
    struct DnnOutputTemp{
        int size;
        std::unique_ptr<T[]> data;

         explicit DnnOutputTemp(int size):size(size),data(std::make_unique<T[]>(size)){};
         explicit DnnOutputTemp()        :size(0),data(std::make_unique<T[]>(0)){};

        T& getData(int index){return data[index];}
        T& getLast(){return data[size-1];}
        T& getFirst(){return data[0];}


        int getSize(){return size;}

        void insert(const T& t);
        void insert(int index,const T& t);

        ~DnnOutputTemp()=default;
    };

    template<typename I ,typename D>
    struct  DnnFaceAlone{  // 单个人脸数据
        I index;
        std::unique_ptr<D[]> data;

        DnnFaceAlone(int _ind,const unsigned char* _data):index(_ind),data(_data){};
        ~DnnFaceAlone()=default;

         void  clear(){index=0;data.reset();}
         void  getIndex(I& i){i=index;}
         void  getData(D& d){d=data;}
    };


    template<class I,class D,class S>
    class DnnFaceOutput{
        int orgSize;
        int maxSize;
        std::unique_ptr<I[]> face_index;
        std::unique_ptr<D[]> face_data;
        std::unique_ptr<S[]> face_dataSize;



    public:
        DnnFaceOutput():orgSize(0),
                        maxSize(16),
                        face_index(std::make_unique<I[]>(maxSize)),
                        face_data(std::make_unique<D[]>(maxSize)),
                        face_dataSize(std::make_unique<S[]>(maxSize))
        {}

        ~DnnFaceOutput()=default;

        void insert( I& i, D& d, S& s);
        void getData(int index,I& i,D& d,S& s);
        int  getSize(){return orgSize;}
    };




}
#endif //SMARTSECURITYCORELIB_DNN_OUTPUT_TEMP_H
